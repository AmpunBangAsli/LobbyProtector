package dev.protector.plugin.lobbyprotector.Yml;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ProtectedArea {
    private static File file;
    private static FileConfiguration customfile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("LobbyProtector").getDataFolder(), "protectedarea.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        customfile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customfile;
    }

    public static void save(){
        try {
            customfile.save(file);
        }catch (IOException e){
            System.out.println("[LobbyProtector] Couldn't save file");
        }
    }

    public static void reload(){
        customfile = YamlConfiguration.loadConfiguration(file);
    }
}
