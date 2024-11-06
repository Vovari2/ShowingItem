package me.vovari2.showingitem.utils;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileUtils {
    public static void createPluginFileInDataFolder(String fileName){
        File dataFolder = createDataFolder();

        File file = new File(dataFolder, fileName);
        if (!file.exists()){
            ShowingItem.getInstance().saveResource(fileName, false);
            Text.sendMessageToConsole("<green>File \"" + fileName + "\" was created!");
        }
    }
    public static void createPluginFileInFolder(String fileName, String folderName){
        createPluginFileInFolder(fileName, folderName, false);
    }
    public static void createPluginFileInFolder(String fileName, String folderName, boolean isReplace){
        File folder = createFolder(folderName);

        File file = new File(folder, fileName);
        if (!file.exists()){
            ShowingItem.getInstance().saveResource(fileName, isReplace);
            Text.sendMessageToConsole("<green>File \"" + fileName + "\" was created!");
        }
    }

    public static File createFolder(String folderName){
        File dataFolder = createDataFolder(),
                folder = new File(dataFolder, folderName);
        if (folder.mkdir())
            Text.sendMessageToConsole("<green>Folder \"" + folderName + "\" in \"" + Text.PLUGIN_NAME + "\" was created!");
        return folder;
    }
    private static File createDataFolder(){
        File dataFolder = ShowingItem.getInstance().getDataFolder();
        if (dataFolder.mkdir())
            Text.sendMessageToConsole("<green>Folder \"" + Text.PLUGIN_NAME + "\" in \"plugins\" was created!");

        return dataFolder;
    }

    public static FileConfiguration getYamlConfiguration(String fileName){
        return YamlConfiguration.loadConfiguration(new File(ShowingItem.getInstance().getDataFolder(), fileName));
    }
}
