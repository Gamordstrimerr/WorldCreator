package me.gamordstrimer.worldcreator.utils;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorldManager {

    private File worldFile;
    private YamlConfiguration config;
    private ArrayList<String> existingWorlds;

    public WorldManager() {
        worldFile = new File("plugins/WorldCreator/worlds.yml");
        config = YamlConfiguration.loadConfiguration(worldFile);
        existingWorlds = new ArrayList<>();
    }

    // ===================================================================================
    // CREATE WORLD
    // ===================================================================================

    public void createWorld(String worldName) {

        List<String> worlds = config.getStringList("worlds"); // create a List of Strings that contains all the world of the worlds.yml
        if (!worlds.contains(worldName)) { // <-- check if the worldName is already used in the worlds.yml
            WorldCreator worldCreator = new WorldCreator(worldName); // <-- use the WorldCreator class to create the world
            worldCreator.environment(World.Environment.NORMAL); // set the environment of the world
            World world = worldCreator.createWorld(); // create the world
            if (world != null) { // check if the world isn't null

                // --- SAVE THE WORLD IN "worlds.yml" ---
                worlds.add(worldName);
                config.set("worlds", worlds);
                try {
                    config.save(worldFile);
                    System.out.println("World Saved in worlds.yml");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        } else {
            System.out.println("you can't register this world because it's already existing.");
        }
    }

    // ===================================================================================
    // LOAD WORLD
    // ===================================================================================

    public void loadWorlds() {
        if (worldFile.exists()) { // check that the File "worlds.yml" exist
            if (config.contains("worlds")) { // check if "world.yml" contains a list name "worlds" (YAML format)
                List<String> configWorlds = config.getStringList("worlds"); // <-- set String list to "worlds" list from "worlds.yml"
                existingWorlds.addAll(configWorlds); // <-- add all the worlds from "configWorlds" to "existingWorlds" list
            }
            // --- Go through each world and load them ---
            for (String worldName : existingWorlds) {
                WorldCreator worldCreator = new WorldCreator(worldName);
                World world = worldCreator.createWorld(); // <-- load the world
                if (world != null) { // SUCCESS
                    System.out.println("World " + worldName + " loaded Successfully !");
                } else { // FAILURE
                    System.out.println("Error loading world -> " + worldName);
                }
            }
        } else {
            System.out.println("No world found to be loaded !");
        }
    }

    // ===================================================================================
    // DELETE WORLD
    // ===================================================================================

    public void deleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName); // <-- select the world from the String in the param of the method
        if (world != null) { // ensure the world is existing.
            File worldDirectory = world.getWorldFolder(); //get the folder of the world
            if (MovePlayertoSpawn(worldName)) {
                Bukkit.unloadWorld(worldName, false); //unload the world
                Bukkit.getScheduler().runTaskLater(me.gamordstrimer.worldcreator.WorldCreator.getInstance(), () -> { // <-- Execute this part 1 sec after (20L)
                    try { // try deleting world directory
                        FileUtils.deleteDirectory(worldDirectory); // <-- delete the world directory using the Apache Commons IO Library
                        deleteWorldFromConfig(worldName); // <-- call the method to erase the world from worlds.yml
                        System.out.println("The directory of " + worldName + " has been deleted!");
                    } catch (IOException e) { //catch error when deleting the world
                        throw new RuntimeException(e);
                    }
                },20L);
            }
        } else {
            System.out.println("The world was not found");
        }
    }


    // --- METHOD TO DELETE WORLD FROM CONFIG ---
    private void deleteWorldFromConfig(String worldName) {
        Bukkit.getScheduler().runTaskLater(me.gamordstrimer.worldcreator.WorldCreator.getInstance(), () -> { // <-- execute this part 1 tick after
            List<String> worlds = config.getStringList("worlds"); // <-- get the list of worlds located in the "worlds" list (in YAML)
            if (worlds == null) { // check if the list isn't empty
                worlds = new ArrayList<>(); // create a new list if empty
            }
            if (worlds.contains(worldName)) { // check that the config contain the world
                worlds.remove(worldName); // remove the world from the config
            }
            config.set("worlds", worlds); // set the list in YAML to the list worlds (in code)
            // --- Try to save the config ---
            try {
                config.save(worldFile);
                System.out.println("the world " + worldName + " has been erased from world.yml");
            } catch (IOException ex) { // catch error
                ex.printStackTrace();
            }
        },1L);
    }


    // --- METHOD TO TELEPORT PLAYER TO SPAWN ---
    private boolean MovePlayertoSpawn(String worldName) {

        /*
        * Maybe make the location of the
        * spawn dynamic through config
        * */
        Location spawn = me.gamordstrimer.worldcreator.WorldCreator.getInstance().getSpawnLocation();
        World world = Bukkit.getWorld(worldName); // <-- get the world with the name ("worldName")

        // for each player in the world, they get teleport to the spawn
        for (Player player : world.getPlayers()) {
            player.teleport(spawn);
        }

        return world.getPlayers().isEmpty(); // return TRUE if the world is empty, FALSE if there is still player in it.
    }
}
