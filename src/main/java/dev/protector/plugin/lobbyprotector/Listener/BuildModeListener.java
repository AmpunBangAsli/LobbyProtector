package dev.protector.plugin.lobbyprotector.Listener;

import dev.protector.plugin.lobbyprotector.Main;
import dev.protector.plugin.lobbyprotector.Util.CC;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedArea;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class BuildModeListener implements Listener {
    public static Main plugin;

    public BuildModeListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if (!plugin.buildmode.contains(p)){
            e.setCancelled(true);
            p.sendMessage(CC.translate("&cYou can't place anything because build mode is off!"));
        }
    }

    @EventHandler
    private void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (!plugin.buildmode.contains(p)){
            e.setCancelled(true);
            p.sendMessage(CC.translate("&cYou can't break anything because build mode is off!"));

        }
    }


}
