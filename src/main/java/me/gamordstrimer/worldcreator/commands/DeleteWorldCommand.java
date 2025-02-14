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
        worldFile = new File("plugins/WorldCreator/worlds.yml");
        config = YamlConfiguration.loadConfiguration(worldFile);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final List<String> defaultworld = Arrays.asList("world", "world_nether", "world_the_end");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                String arg1 = args[0].toLowerCase();
                if (!defaultworld.contains(arg1)) {
                    // RELOAD CONFIG BEFORE READING
                    config = YamlConfiguration.loadConfiguration(worldFile);
                    List<String> existingWorlds = config.getStringList("worlds");

                    if (existingWorlds.contains(arg1)) {
                        new WorldManager().deleteWorld(arg1);
                        String message = String.join("\n",
                                messageBorder,
                                ChatColor.RED + "➤ The world " + ChatColor.DARK_RED + arg1 + ChatColor.RED + " has been deleted!",
                                messageBorder);

                        player.sendMessage(message);
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "⚠" + ChatColor.RESET + ChatColor.RED + " ➡ This world is " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "PROTECTED");
                    return true;
                }
            }
        }
        return false;
    }
}
