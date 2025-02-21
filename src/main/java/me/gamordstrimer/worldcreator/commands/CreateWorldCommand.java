package me.gamordstrimer.worldcreator.commands;

import me.gamordstrimer.worldcreator.utils.WorldManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateWorldCommand implements CommandExecutor {

    final String messageBorder = ChatColor.GREEN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // protection for console
            Player player = (Player) sender;
            if (args.length == 1) { // check that there is only one argument to the command
                String worldName = args[0].toLowerCase(); // set the first argument to lower case

                // DEFINE THE MESSAGE
                String message = String.join("\n",
                        messageBorder,
                        " ",
                        ChatColor.YELLOW + "   Creation of world ➡ " + ChatColor.GOLD + ChatColor.BOLD + worldName + ChatColor.YELLOW + " has started.",
                        ChatColor.YELLOW + "   Check console for more informations...",
                        " ",
                        messageBorder
                        );

                player.sendMessage(message); // send the "message to the player"
                new WorldManager().createWorld(worldName); // call the class "me.gamordstrimer.worldcreator.utils.WorldManager" to get the method to create the world
                player.sendMessage(ChatColor.GREEN + "➤ The world " + ChatColor.YELLOW + worldName + ChatColor.GREEN + " has been created!"); // message send once the world is created
                return true;
            }
        }
        return false;
    }
}
