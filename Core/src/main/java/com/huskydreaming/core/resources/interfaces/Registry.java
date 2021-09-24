package com.huskydreaming.core.resources.interfaces;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public interface Registry {

    Map<String, List<String>> registries = new HashMap<>();
    Set<Manager> managers = new HashSet<>();

    default void register(Plugin plugin) {
        registries.putIfAbsent(plugin.getName(), new ArrayList<>());
        registries.get(plugin.getName()).add(getClass().getSimpleName());
    }

    static void Register(Plugin plugin, Registry... registries) {
        Arrays.asList(registries).forEach(registry -> registry.register(plugin));
    }

    static void registries(Plugin plugin) {
        List<String> instances = registries.get(plugin.getName());
        plugin.getLogger().info("Registered " + instances.size() + " instances: " + Arrays.toString(instances.toArray()));
    }

    static void deserializeManagers() {
        managers.forEach(Manager::deserialize);
    }

    static void serializeManagers() {
        managers.forEach(Manager::serialize);
    }

    static void registerManagers(Plugin plugin, Class<?>... classes) {
        String[] strings = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            try {
                Constructor<?> constructor = classes[i].getConstructor(Plugin.class);

                Manager manager = (Manager) constructor.newInstance(plugin);
                managers.add(manager);
                strings[i] = manager.getClass().getSimpleName();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        plugin.getLogger().info("Registered " + strings.length + " managers: " + Arrays.toString(strings));
    }
}
