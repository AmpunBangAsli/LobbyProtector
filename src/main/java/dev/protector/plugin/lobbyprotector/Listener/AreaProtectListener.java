package dev.protector.plugin.lobbyprotector.Listener;

import dev.protector.plugin.lobbyprotector.Main;
import dev.protector.plugin.lobbyprotector.Util.CC;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedArea;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class AreaProtectListener implements Listener {
    public static Main plugin;

    public AreaProtectListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    private void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        ConfigurationSection regions = ProtectedArea.get().getConfigurationSection("protectedarea");


        regions.getKeys(false).forEach(key ->{
            int X1 = ProtectedArea.get().getInt("protectedarea." + key + ".x1");
            int Y1 = ProtectedArea.get().getInt("protectedarea." + key + ".y1");
            int Z1 = ProtectedArea.get().getInt("protectedarea." + key + ".z1");
            int X2 = ProtectedArea.get().getInt("protectedarea." + key + ".x2");
            int Y2 = ProtectedArea.get().getInt("protectedarea." + key + ".y2");
            int Z2 = ProtectedArea.get().getInt("protectedarea." + key + ".z2");
            World world = Bukkit.getWorld(ProtectedArea.get().getString("protectedarea." + key + ".world"));
            Validate.notNull(world, "Can't find the world!");
            Location loc1 = new Location(world, X1, Y1, Z1);
            Location loc2 = new Location(world, X2, Y2, Z2);
            Cuboid cuboid = new Cuboid(loc1, loc2);
            if (cuboid.contains(e.getBlock().getLocation())) {
                if (!p.hasPermission("lobbyprotector.bypassarea")) {
                    e.setCancelled(true);
                    p.sendMessage(CC.translate("&cYou can't break this area!"));
                }
            }
        });


    }

    @EventHandler
    private void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        ConfigurationSection regions = ProtectedArea.get().getConfigurationSection("protectedarea");


        regions.getKeys(false).forEach(key ->{
            int X1 = ProtectedArea.get().getInt("protectedarea." + key + ".x1");
            int Y1 = ProtectedArea.get().getInt("protectedarea." + key + ".y1");
            int Z1 = ProtectedArea.get().getInt("protectedarea." + key + ".z1");
            int X2 = ProtectedArea.get().getInt("protectedarea." + key + ".x2");
            int Y2 = ProtectedArea.get().getInt("protectedarea." + key + ".y2");
            int Z2 = ProtectedArea.get().getInt("protectedarea." + key + ".z2");
            String worldname = ProtectedArea.get().getString("protectedarea." + key + ".world");
            World world = Bukkit.getWorld(worldname);
            Validate.notNull(world, "NULL");
            Location loc1 = new Location(world, X1, Y1, Z1);
            Location loc2 = new Location(world, X2, Y2, Z2);
            Cuboid cuboid = new Cuboid(loc1, loc2);
            if (cuboid.contains(e.getBlock().getLocation())) {
                if (!p.hasPermission("lobbyprotector.bypassarea")) {
                    e.setCancelled(true);
                    p.sendMessage(CC.translate("&cYou can't place block on this area!"));
                }
            }
        });


    }
}
