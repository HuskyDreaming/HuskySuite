package com.huskydreaming.essentials.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();
}
