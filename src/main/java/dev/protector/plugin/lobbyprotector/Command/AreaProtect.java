package dev.protector.plugin.lobbyprotector.Command;

import dev.protector.plugin.lobbyprotector.Main;
import dev.protector.plugin.lobbyprotector.Util.CC;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedArea;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;

import static dev.protector.plugin.lobbyprotector.Util.CC.translate;

public class AreaProtect implements CommandExecutor {

    public static Main plugin;

    public AreaProtect(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(CC.translate("&cOnly player can do this!"));
            return true;
        }

        if (command.getName().equalsIgnoreCase("setuparea")){
            if (sender.hasPermission("lobbyprotector.setuparena")) {
                if (args.length == 0) {
                    sender.sendMessage(translate("&cHow to use: /setuparea <areaname>"));
                    return true;
                }
                ConfigurationSection area = ProtectedArea.get().getConfigurationSection("protectedarea");

                if (area.getConfigurationSection(args[0]) != null) {
                    sender.sendMessage(translate("&cThe area is already exist!"));
                    return true;
                }

                Player p = (Player) sender;
                ProtectedArea.get().getConfigurationSection("protectedarea").createSection(args[0]);
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("world", p.getWorld().getName());
                sender.sendMessage(translate("&aSaved the name!, Use /areapos1 and /areapos2 to add location coordinate"));
                plugin.setup.add(p);
                ProtectedArea
                        .save();
            } else {
                sender.sendMessage(CC.translate("&cYou don't have any permission to do that!"));
            }
        } else if (command.getName().equalsIgnoreCase("areapos1")){
            if (sender.hasPermission("lobbyprotector.areapos1")) {
                if (args.length == 0) {
                    sender.sendMessage(translate("&cHow to use: /areapos1 <areaname>"));
                    return true;
                }
                if (ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0]) == null) {
                    sender.sendMessage(translate("&cCan't find " + args[0] + "!"));
                    return true;
                }
                Player p = (Player) sender;
                if (!plugin.setup.contains(p)) {
                    sender.sendMessage(translate("&cYou are not on setup mode!"));
                    return true;
                }
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("x1", p.getLocation().getX());
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("y1", 0);
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("z1", p.getLocation().getZ());
                ProtectedArea
                        .save();
                sender.sendMessage(CC.translate("&aSaved location 1 to config!"));
            } else {
                sender.sendMessage(CC.translate("&cYou don't have any permission to do that!"));
            }
        } else if (command.getName().equalsIgnoreCase("areapos2")) {
            if (sender.hasPermission("lobbyprotector.areapos2")) {
                if (args.length == 0) {
                    sender.sendMessage(translate("&cHow to use: /areapos2 <areaname>"));
                    return true;
                }
                if (ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0]) == null) {
                    sender.sendMessage(translate("&cCan't find " + args[0] + "!"));
                    return true;
                }
                Player p = (Player) sender;
                if (!plugin.setup.contains(p)) {
                    sender.sendMessage(translate("&cYou are not on setup mode!"));
                    return true;
                }
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("x2", p.getLocation().getX());
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("y2", p.getWorld().getMaxHeight());
                ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0])
                        .set("z2", p.getLocation().getZ());
                ProtectedArea
                        .save();
                sender.sendMessage(CC.translate("&aSaved location 2 to config!"));
                plugin.setup.remove(p);
            } else {
                sender.sendMessage(CC.translate("&cYou don't have any permission to do that!"));
            }
        } else if (command.getName().equalsIgnoreCase("deletearea")) {
            if (sender.hasPermission("lobbyprotector.deletearea")){
                if (ProtectedArea.get().getConfigurationSection("protectedarea").getConfigurationSection(args[0]) == null){
                    sender.sendMessage(CC.translate("&cCan't find area with name: '" + args[0] + "'!"));
                    return true;
                }

                ProtectedArea.get().getConfigurationSection("protectedarea").set(args[0], null);
                ProtectedArea.save();
                sender.sendMessage(CC.translate("&aDeleted '" + args[0] + "'!"));

            } else {
                sender.sendMessage(CC.translate("&cYou don't have any permission to do that!"));
            }
        }

        return true;
    }

}
