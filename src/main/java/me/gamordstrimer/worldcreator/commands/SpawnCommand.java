package me.gamordstrimer.worldcreator.commands;

import me.gamordstrimer.worldcreator.WorldCreator;
import me.gamordstrimer.worldcreator.utils.Spawn;
import me.gamordstrimer.worldcreator.utils.SpawnStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            SpawnStorage storage = new SpawnStorage();
            Spawn spawn = new Spawn();
            String commandName = command.getName();

            switch (commandName) {
                case "spawn":
                    spawn.sendToSpawn(player);
                    break;
                case "setspawn":
                    storage.storeSpawn(player.getLocation());
                    player.sendMessage(ChatColor.GOLD + "➤ " + ChatColor.YELLOW + "the spawn of the server has been set.");
                    break;
            }
            return true;

        } else {
            sender.sendMessage("You must be a player to use this command!");
        }
        return false;
    }
}
