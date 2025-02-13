package me.gamordstrimer.worldcreator.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String worldName = player.getWorld().getName();
            player.sendMessage(ChatColor.YELLOW + "You are in the world ➡ " + ChatColor.GOLD + worldName );
            return true;
        }
        return false;
    }
}
