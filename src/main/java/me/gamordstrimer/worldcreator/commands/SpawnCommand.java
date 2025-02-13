package me.gamordstrimer.worldcreator.commands;

import me.gamordstrimer.worldcreator.WorldCreator;
import org.bukkit.Bukkit;
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
            Location spawn = WorldCreator.getInstance().getSpawnLocation();

            player.teleport(spawn);
            /*
            *
            * Maybe add the possibility to do /setspawn
            * to define the Location of the Spawn
            *  (Config)
            *
            * */
            return true;
        }
        return false;
    }
}
