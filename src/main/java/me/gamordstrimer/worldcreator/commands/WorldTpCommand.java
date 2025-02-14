package me.gamordstrimer.worldcreator.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WorldTpCommand implements CommandExecutor {

    private File worldFile;
    private YamlConfiguration config;

    public WorldTpCommand() {
        worldFile = new File("plugins/WorldCreator/worlds.yml");
        config = YamlConfiguration.loadConfiguration(worldFile);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {

                String arg1 = args[0].toLowerCase();
                if (!(arg1.equalsIgnoreCase(player.getWorld().getName()))) {
                    // if the first argument (args[0]) of the command is "list" then it execute this code
                    if (arg1.equalsIgnoreCase("list")) {
                        if (Bukkit.getWorlds().size() > 3) {
                            player.sendMessage(ChatColor.GOLD + "Worlds of the Server :");
                            List<String> defaultworld = Arrays.asList("world", "world_nether", "world_the_end"); // contain all the default world of minecraft
                            for (World world : Bukkit.getWorlds()) { // <-- check each world of the server
                                if (!defaultworld.contains(world.getName())) { // <-- check that the world isn't in the defaultworld list
                                    player.sendMessage(ChatColor.YELLOW + "- " + ChatColor.GREEN + world.getName());
                                    /*
                                     *  I could try to make each world clickable
                                     *  so if the player click one he get teleport
                                     *  to the world.
                                     *
                                     *  example video : https://www.youtube.com/watch?v=yVBFAx-qjkQ
                                     *  usage of "TextComponent".
                                     *
                                     * */
                                }
                            }
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "➤ No world has been created for now...");
                            return true;
                        }
                    } else {
                        if (Bukkit.getWorld(arg1) != null) {
                            player.teleport(new Location(Bukkit.getWorld(arg1), 0, 100, 0)); //default location for each new world
                            player.sendMessage(ChatColor.YELLOW + "You got teleport to " + ChatColor.GOLD + ChatColor.BOLD + arg1);
                        } else {
                            // System.out.println("This world doesn't exist!");
                            player.sendMessage(ChatColor.RED + "the world specified doesn't exist. ");
                        }
                    return true;
                }
                } else {
                    player.sendMessage("You can't teleport in this world, because you are already in it!");
                }
            }
        }
        return false;
    }
}
