package dev.protector.plugin.lobbyprotector.Command;

import dev.protector.plugin.lobbyprotector.Main;
import dev.protector.plugin.lobbyprotector.Util.CC;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedArea;
import dev.protector.plugin.lobbyprotector.Yml.ProtectedWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UtilCommand implements CommandExecutor {
    public static Main plugin;

    public UtilCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(CC.translate("&6/lobbyprotect help <- basic help command"));
            sender.sendMessage(CC.translate("&6/lobbyprotect about <- about the plugin"));
            sender.sendMessage(CC.translate("&6/lobbyprotect author <- to see the author(you can't modify the author name)"));
            sender.sendMessage(CC.translate("&6/lobbyprotect reload <- to reload your plugin"));
            return true;
        }

        if (args[0].equalsIgnoreCase("help")){
            sender.sendMessage(CC.translate("&6/lobbyprotect help <- basic help command"));
            sender.sendMessage(CC.translate("&6/lobbyprotect about <- about the plugin"));
            sender.sendMessage(CC.translate("&6/lobbyprotect author <- to see the author(you can't modify the author name)"));
            sender.sendMessage(CC.translate("&6/lobbyprotect reload <- to reload your plugin"));
        } else if (args[0].equalsIgnoreCase("about")) {
            sender.sendMessage(CC.translate("&6This plugin made by AmpunBang_ for AmpNetwork"));
        } else if (args[0].equalsIgnoreCase("author")){
            sender.sendMessage(CC.translate("&6TheDevTeam (Specially AmpunBang_)"));
        } else if (args[0].equalsIgnoreCase("reload")){
            ProtectedWorld.reload();
            ProtectedArea.reload();
            plugin.reloadConfig();
            sender.sendMessage(CC.translate("&aReloaded Config!"));
        }


        return true;
    }
}
