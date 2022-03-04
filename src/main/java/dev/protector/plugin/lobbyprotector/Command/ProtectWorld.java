package dev.protector.plugin.lobbyprotector.Command;

import dev.protector.plugin.lobbyprotector.Util.CC;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProtectWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("lobbyprotector.protectworld") || sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(CC.translate("&cHow to use: /protectworld <world>"));
                    return true;
                }
                if (ProtectedWorld.get().getConfigurationSection(args[0]) != null) {
                    sender.sendMessage(CC.translate("&cThe world is already protected!"));
                    return true;
                }

                if (Bukkit.getWorld(args[0]) == null){
                    sender.sendMessage(CC.translate("&cCant find world with name " + args[0]));
                    return true;
                }

                ProtectedWorld.get().createSection(args[0]);
                ProtectedWorld.get().getConfigurationSection(args[0]).set("pvp", false);
                ProtectedWorld.get().getConfigurationSection(args[0]).set("mobspawn", false);
                ProtectedWorld.save();
                sender.sendMessage(CC.translate("&aWorld " + args[0] + " has been protected!"));

            } else {
                sender.sendMessage(CC.translate("&cYou don't have any permission to do this!"));
            }


        } else {
            sender.sendMessage(CC.translate("&cSorry! This command only executed by player!"));
        }

        return true;
    }
}
