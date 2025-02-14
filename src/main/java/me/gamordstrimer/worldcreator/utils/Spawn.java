package me.gamordstrimer.worldcreator.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

    private final SpawnStorage spawnStorage = new SpawnStorage();

    public void sendToSpawn(Player player) {
        Location spawnLocation = spawnStorage.loadSpawn();
        if (spawnLocation != null) {
            player.teleport(spawnLocation); // teleport the player to the spawn
            player.sendMessage(ChatColor.GOLD + "➤ " + ChatColor.YELLOW + "You have been teleported to the " + ChatColor.GREEN + "Spawn.");
        }
    }
}
