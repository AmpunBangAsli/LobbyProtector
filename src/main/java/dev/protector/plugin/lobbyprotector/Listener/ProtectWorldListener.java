package dev.protector.plugin.lobbyprotector.Listener;

import dev.protector.plugin.lobbyprotector.Util.CC;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedWorld;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;
import java.util.*;

public class ProtectWorldListener implements Listener {

    @EventHandler
    private void onMobSpawn(CreatureSpawnEvent e){
        FileConfiguration config = ProtectedWorld.get();

        Location location = e.getLocation();
        World world = location.getWorld();
        Validate.notNull(world, "Invalid World.");
        String name = world.getName();

        if (config.getConfigurationSection(name) == null) return;

        ConfigurationSection section = config.getConfigurationSection(name);
        Validate.notNull(section, "Invalid configuration section for protected world '" + name + "'");

        boolean allowMobs = section.getBoolean("mobspawn");

        if (!allowMobs && e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)){
            e.setCancelled(true);
        }

    }

    @EventHandler
    private void onPvp(EntityDamageByEntityEvent e) {
        FileConfiguration config = ProtectedWorld.get();

        Player p = (Player) e.getDamager();
        Location location = p.getLocation();
        World world = location.getWorld();
        Validate.notNull(world, "Invalid World.");
        String name = world.getName();

        ConfigurationSection section = config.getConfigurationSection(name);

        if (section == null) return;

        Validate.notNull(section, "Invalid configuration section for protected world '" + name + "'");

        boolean allowpvp = section.getBoolean("pvp");

        if (!allowpvp && e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER)){
            p.sendMessage(CC.translate("&cYou can't pvp here!"));
            e.setCancelled(true);
        }

    }

}
