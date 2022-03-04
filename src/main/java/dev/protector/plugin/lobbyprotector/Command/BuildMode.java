package dev.protector.plugin.lobbyprotector.Command;

import dev.protector.plugin.lobbyprotector.Main;
import dev.protector.plugin.lobbyprotector.Util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildMode implements CommandExecutor {
    public static Main plugin;

    public BuildMode(Main plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 1) {
                if (p.hasPermission("lobbyprotector.buildmodeother") || p.isOp()) {
                    if (args[0].equalsIgnoreCase("help")){
                        sender.sendMessage(CC.translate("§6|------------------------------------------------|"));
                        sender.sendMessage(CC.translate("&6/buildmode -> Make your build mode to true/false"));
                        sender.sendMessage(CC.translate("&6/buildmode <target> -> Make Target build mode to true/false"));
                        sender.sendMessage(CC.translate("§6|------------------------------------------------|"));
                        return true;
                    }
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null){
                        sender.sendMessage(CC.translate("&cCan't find &7'" + args[0] + "'&c!"));
                        return true;
                    }

                    if (plugin.buildmode.contains(target)) {
                        plugin.buildmode.remove(target);
                        target.sendMessage(CC.translate("&6Build Mode: &coff"));
                        p.sendMessage(CC.translate("&6Build Mode for " + args[0] + ": &coff"));
                    } else {
                        plugin.buildmode.add(target);
                        target.sendMessage(CC.translate("&6Build Mode: &aon"));
                        p.sendMessage(CC.translate("&6Build Mode for " + args[0] + ": &aon"));
                    }
                } else {
                    sender.sendMessage(CC.translate("&cYou don't have any permission to do that!"));
                }
                return true;
            }



            if (p.hasPermission("lobbyprotector.buildmode") || p.isOp()) {
                if (plugin.buildmode.contains(p)) {
                    plugin.buildmode.remove(p);
                    p.sendMessage(CC.translate("&6Build Mode: &coff"));
                } else {
                    plugin.buildmode.add(p);
                    p.sendMessage(CC.translate("&6Build Mode: &aon"));
                }
            } else {
                sender.sendMessage(CC.translate("&cYou don't have any permission to do that!"));
            }


        } else {
            sender.sendMessage(CC.translate("&cOnly players can do this!"));
        }


        return false;
    }
}
