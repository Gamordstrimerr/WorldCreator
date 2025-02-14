package me.gamordstrimer.worldcreator.commands;

import me.gamordstrimer.worldcreator.utils.WorldManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DeleteWorldCommand implements CommandExecutor {

    private File worldFile;
    private YamlConfiguration config;
    final String messageBorder = ChatColor.RED + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    public DeleteWorldCommand() {
        worldFile = new File("plugins/WorldCreator/worlds.yml"); // get the worldFile --> worlds.yml
        config = YamlConfiguration.loadConfiguration(worldFile);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final List<String> defaultworld = Arrays.asList("world", "world_nether", "world_the_end"); // default worlds of Minecraft.

        if (sender instanceof Player) { // check that the sender of the command is a player (command can't be executed by console).
            Player player = (Player) sender; // player --> sender
            if (args.length == 1) { // check that there is only one argument to the command
                String arg1 = args[0].toLowerCase(); // set the first argument of the command to "arg1" and to Lower case
                if (!defaultworld.contains(arg1)) { // security to protect the default world of minecraft.
                    // RELOAD CONFIG BEFORE READING
                    config = YamlConfiguration.loadConfiguration(worldFile);
                    List<String> existingWorlds = config.getStringList("worlds");

                    if (existingWorlds.contains(arg1)) {
                        new WorldManager().deleteWorld(arg1); // call "me.gamordstrimer.worldcreator.utils.WorldManager" to get the method to delete the world

                        // Define the message
                        String message = String.join("\n",
                                messageBorder,
                                ChatColor.RED + "➤ The world " + ChatColor.DARK_RED + arg1 + ChatColor.RED + " has been deleted!",
                                messageBorder);

                        player.sendMessage(message); // send the "message" to the player
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "the world specified doesn't exist. ");
                        return true;
                    }
                } else {
                    // protection message for Minecraft default world
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "⚠" + ChatColor.RESET + ChatColor.RED + " ➡ This world is " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "PROTECTED");
                    return true;
                }
            }
        }
        return false;
    }
}
