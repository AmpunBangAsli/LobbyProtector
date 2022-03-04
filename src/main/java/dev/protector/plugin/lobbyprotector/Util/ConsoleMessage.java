package dev.protector.plugin.lobbyprotector.Util;

import dev.protector.plugin.lobbyprotector.Main;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

public class ConsoleMessage {


    public static void StartMSG(){
        System.out.println("§a|------------------------------------------------|");
        System.out.println("§e§lLobbyProtector §2/ §6Hello!");
        System.out.println("§6Currently version: 1.0");
        System.out.println("§6Server version: " + Bukkit.getServer().getVersion());
        System.out.println("§a|------------------------------------------------|");
    }

    public static void DisableMessage(){
        System.out.println("§a|------------------------------------------------|");
        System.out.println("§e§lLobbyProtector §2/ §6Bye!, Have a nice day :)");
        System.out.println("§a|------------------------------------------------|");
    }


}
