package me.gamordstrimer.worldcreator.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

    private final SpawnStorage spawnStorage = new SpawnStorage();

    // --- METHOD TO SEND THE PLAYER TO THE SPAWN ---
    public void sendToSpawn(Player player) {
        Location spawnLocation = spawnStorage.loadSpawn(); // <-- get the spawn location from "spawn.yml"
        if (spawnLocation != null) { // <-- check that the spawn location isn't NULL
            player.teleport(spawnLocation); // teleport the player to the spawn
            //message for the player when he's teleported to the spawn
            player.sendMessage(ChatColor.GOLD + "➤ " + ChatColor.YELLOW + "You have been teleported to the " + ChatColor.GREEN + "Spawn.");
        }
    }
}
