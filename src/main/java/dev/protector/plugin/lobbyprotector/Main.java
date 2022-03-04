package dev.protector.plugin.lobbyprotector;

import dev.protector.plugin.lobbyprotector.Command.*;
import dev.protector.plugin.lobbyprotector.Listener.AreaProtectListener;
import dev.protector.plugin.lobbyprotector.Listener.BuildModeListener;
import dev.protector.plugin.lobbyprotector.Listener.Cuboid;
import dev.protector.plugin.lobbyprotector.Listener.ProtectWorldListener;
import dev.protector.plugin.lobbyprotector.Util.ConsoleMessage;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedArea;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {
    public ArrayList<Player> buildmode = new ArrayList<>();
    public ArrayList<Player> setup = new ArrayList<>();
    public FileConfiguration config = this.getConfig();

    public Cuboid cuboid;
    @Override
    public void onEnable() {
        // Plugin startup logic
        if (getServer().getPluginManager().getPlugin("BuildEssentials") == null){
            getLogger().info("§e§lLobbyProtector §2/ §cBuildEssentials not found!, Disabling this plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        ConsoleMessage.StartMSG();
        getCommand("buildmode").setExecutor(new BuildMode(this));
        getCommand("protectworld").setExecutor(new ProtectWorld());
        getCommand("lobbyprotector").setExecutor(new UtilCommand(this));
        getCommand("setuparea").setExecutor(new AreaProtect(this));
        getCommand("areapos1").setExecutor(new AreaProtect(this));
        getCommand("areapos2").setExecutor(new AreaProtect(this));
        getCommand("deletearea").setExecutor(new AreaProtect(this));
        getServer().getPluginManager().registerEvents(new BuildModeListener(this), this);
        getServer().getPluginManager().registerEvents(new ProtectWorldListener(), this);
        getServer().getPluginManager().registerEvents(new AreaProtectListener(this), this);
        ProtectedWorld.setup();
        ProtectedArea.setup();
        if (ProtectedArea.get().getConfigurationSection("protectedarea") == null){
            ProtectedArea.get().createSection("protectedarea");
            return;
        }
        System.out.println(ProtectedArea.get().getString("protectedarea." + "lobby" + ".world"));


        /**
         * Checking region and make a new region.
         * Currently I don't use this.
         */
        ConfigurationSection regions = ProtectedArea.get().getConfigurationSection("protectedarea");


        regions.getKeys(false).forEach(key -> {
            int X1 = ProtectedArea.get().getInt("protectedarea." + key + ".x1");
            int Y1 = ProtectedArea.get().getInt("protectedarea." + key + ".y1");
            int Z1 = ProtectedArea.get().getInt("protectedarea." + key + ".z1");
            int X2 = ProtectedArea.get().getInt("protectedarea." + key + ".x2");
            int Y2 = ProtectedArea.get().getInt("protectedarea." + key + ".y2");
            int Z2 = ProtectedArea.get().getInt("protectedarea." + key + ".z2");
            Location loc1 = new Location(Bukkit.getWorld("creativeworld"), X1, Y1, Z1);
            Location loc2 = new Location(Bukkit.getWorld("creativeworld"), X2, Y2, Z2);
            cuboid = new Cuboid(loc1, loc2);
        });
    }

    @Override
    public void onDisable() {
        saveConfig();
        ProtectedWorld.save();
        ProtectedArea.save();
        ConsoleMessage.DisableMessage();
    }

}
