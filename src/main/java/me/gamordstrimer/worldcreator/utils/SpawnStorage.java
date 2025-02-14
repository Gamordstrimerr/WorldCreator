package me.gamordstrimer.worldcreator.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnStorage {

    private File spawnFile;
    private YamlConfiguration config;

    public SpawnStorage() {
        spawnFile = new File("plugins/WorldCreator/spawn.yml");
        config = YamlConfiguration.loadConfiguration(spawnFile);
    }

    public void storeSpawn(Location spawnLocation) {
        // Set yaw and pitch to 0.0
        spawnLocation.setYaw(0.0f);
        spawnLocation.setPitch(0.0f);

        // Store location in YAML file
        config.set("spawn.location.world", spawnLocation.getWorld().getName());
        config.set("spawn.location.x", spawnLocation.getX());
        config.set("spawn.location.y", spawnLocation.getY());
        config.set("spawn.location.z", spawnLocation.getZ());
        config.set("spawn.location.yaw", spawnLocation.getYaw());
        config.set("spawn.location.pitch", spawnLocation.getPitch());

        try {
            config.save(spawnFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Location loadSpawn() {
        if (spawnFile.exists()) {
            if (config.contains("spawn.location")) {
                String worldName = config.getString("spawn.location.world");
                double x = config.getDouble("spawn.location.x");
                double y = config.getDouble("spawn.location.y");
                double z = config.getDouble("spawn.location.z");
                float yaw = (float) config.getDouble("spawn.location.yaw");
                float pitch = (float) config.getDouble("spawn.location.pitch");
                return new Location(Bukkit.getWorld(worldName),x,y,z, yaw, pitch);
            }
        } else {
            System.out.println("Could not find spawn file");
        }
        return null;
    }
}
