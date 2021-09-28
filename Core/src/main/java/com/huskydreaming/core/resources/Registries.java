package com.huskydreaming.core.resources;

import com.huskydreaming.core.resources.interfaces.Manager;
import com.huskydreaming.core.resources.interfaces.Registry;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Registries {

    private static final Map<String, List<String>> registries = new HashMap<>();
    private static final Set<Manager> managers = new HashSet<>();

    private Registries() {

    }

    public static void register(Plugin plugin, String name) {
        registries.putIfAbsent(plugin.getName(), new ArrayList<>());
        registries.get(plugin.getName()).add(name);
    }

    public static void register(Plugin plugin, Registry... registries) {
        Arrays.asList(registries).forEach(registry -> registry.register(plugin));
    }

    public static void log(Plugin plugin) {
        List<String> instances = registries.get(plugin.getName());
        plugin.getLogger().info("Registered " + instances.size() + " instances: " + Arrays.toString(instances.toArray()));
    }

    public static void deserialize() {
        managers.forEach(Manager::deserialize);
    }

    public static void serialize() {
        managers.forEach(Manager::serialize);
    }

    public static void register(Plugin plugin, Class<?>... classes) {
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
