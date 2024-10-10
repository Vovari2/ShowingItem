package me.vovari2.showingitem.utils;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigUtils {
    private static FileConfiguration configuration;

    public static void createFile(){
        File dataFolder = ShowingItem.getInstance().getDataFolder();

        if (dataFolder.mkdir())
            Text.sendMessageToConsole("<green>Folder \"ShowingItem\" in \"plugins\" was created!");

        File file = new File(dataFolder, "config.yml");
        if (!file.exists()){
            ShowingItem.getInstance().saveResource("config.yml", false);
            Text.sendMessageToConsole("<green>File \"config.yml\" was created!");
        }
    }
    public static void loadConfiguration(){
        configuration = YamlConfiguration.loadConfiguration(new File(ShowingItem.getInstance().getDataFolder(), "config.yml"));
    }


    public static boolean getEnable() {
        return configuration.getBoolean("enable", false);
    }
    public static String getMatcher(){
        return configuration.getString("matcher", "[item]");
    }
    public static Component getReplacement(){
        return MiniMessage.miniMessage().deserialize(configuration.getString("replacement", "<%item%>"));
    }

}
