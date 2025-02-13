package me.gamordstrimer.worldcreator.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ListWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<String> defaultworld = Arrays.asList("world", "world_nether", "world_the_end"); // contain all the default world of minecraft
            if (Bukkit.getWorlds().size() > 3) {
                player.sendMessage(ChatColor.GOLD + "➤ Worlds of the Server :");
                for(World world : Bukkit.getWorlds()) { // <-- check each world of the server
                    if (!defaultworld.contains(world.getName())) { // <-- check that the world isn't in the defaultworld list
                        player.sendMessage(ChatColor.YELLOW + "- " + ChatColor.GREEN + world.getName());
                    }
                }
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "➤ No world has been created for now...");
                return true;
            }
        }
        return false;
    }
}
