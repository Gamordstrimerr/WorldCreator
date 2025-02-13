package me.gamordstrimer.worldcreator.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    private String joinMessage;
    private String leaveMessage;

    public PlayerListeners() {
        joinMessage = ChatColor.GREEN + " has join the game.";
        leaveMessage = ChatColor.RED + " has quit the game.";
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = (Player) event.getPlayer();
        event.setJoinMessage(ChatColor.DARK_GREEN + player.getName() + joinMessage);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = (Player) event.getPlayer();
        event.setQuitMessage(ChatColor.DARK_RED + player.getName() + leaveMessage);
    }
}
