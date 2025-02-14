package me.gamordstrimer.worldcreator;

import me.gamordstrimer.worldcreator.commands.*;
import me.gamordstrimer.worldcreator.listeners.PlayerListeners;
import me.gamordstrimer.worldcreator.utils.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldCreator extends JavaPlugin {

    private static WorldCreator instance;
    private Location spawn;

    // ===================================================================================
    // WHEN THE PLUGIN START
    // ===================================================================================

    @Override
    public void onEnable() {
        System.out.println("WorldCreator On!");

        instance = this;
        spawn = new Location(Bukkit.getWorld("world"), -56,72,201); // define the spawn location
        new WorldManager().loadWorlds(); //load the worlds contain in the worldFile

        // Register differents Assets
        registerEvent();
        registerCommands();
    }

    // ===================================================================================
    // WHEN THE PLUGIN STOP
    // ===================================================================================

    @Override
    public void onDisable() {
        System.out.println("WorldCreator Off!");
    }

    // ===================================================================================
    // FUNCTION TO REGISTER COMMANDS & EVENTS
    // ===================================================================================

    private void registerEvent() {
        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    private void registerCommands() {
        getCommand("create-world").setExecutor(new CreateWorldCommand());
        getCommand("delete-world").setExecutor(new DeleteWorldCommand());
        getCommand("teleport-world").setExecutor(new WorldTpCommand());
        getCommand("get-world").setExecutor(new GetWorldCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SpawnCommand());
        getCommand("list-world").setExecutor(new ListWorldCommand());
    }

    // ===================================================================================
    // GETTER TO GET THE INSTANCE OF THE MAIN CLASS
    // ===================================================================================

    public static WorldCreator getInstance() {
        return instance;
    }

    // ===================================================================================
    // GETTER FOR OTHER VARIABLES
    // ===================================================================================

    public Location getSpawnLocation() {
        return spawn;
    }
}
