package me.gamordstrimer.worldcreator.commands;

import me.gamordstrimer.worldcreator.utils.Spawn;
import me.gamordstrimer.worldcreator.utils.SpawnStorage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender; // <-- get the player that executed the command
            // call the Class for spawn & spawnstorage
            SpawnStorage storage = new SpawnStorage();
            Spawn spawn = new Spawn();

            String commandName = command.getName(); // <-- get the command

            switch (commandName) {
                case "spawn":
                    spawn.sendToSpawn(player); // call method from the class "me.gamordstrimer.worldcreator.utils.Spawn"
                    break;
                case "setspawn":
                    storage.storeSpawn(player.getLocation()); // call method from the class "me.gamordstrimer.worldcreator.utils.SpawnStorage"
                    player.sendMessage(ChatColor.GOLD + "➤ " + ChatColor.YELLOW + "the spawn of the server has been set."); // <-- send message to the player once the spawn is saved.
                    break;
            }
            return true;

        } else {
            sender.sendMessage("You must be a player to use this command!");
        }
        return false;
    }
}
