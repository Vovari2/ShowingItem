package me.vovari2.showingitem;

import me.vovari2.showingitem.commands.Executor;
import me.vovari2.showingitem.commands.TabCompleter;
import me.vovari2.showingitem.utils.ConfigUtils;
import me.vovari2.showingitem.utils.TextUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class ShowingItem extends JavaPlugin {
    private static ShowingItem instance;
    private boolean isEnable;

    private String matcher;
    private Component replacement;

    @Override
    public void onEnable() {
        instance = this;

        PluginCommand command = instance.getCommand("showingitem");
        if (command != null){
            command.setExecutor(new Executor());
            command.setTabCompleter(new TabCompleter());
        }
        else Text.sendMessageToConsole("<red>Command /showingitem (/si) must not be null!");

        Text.initialize(getServer().getConsoleSender());
        TextUtils.loadTexts();

        ConfigUtils.createFile();
        ConfigUtils.loadConfiguration();
        isEnable = ConfigUtils.getEnable();
        matcher = ConfigUtils.getMatcher();
        replacement = ConfigUtils.getReplacement();

        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        Text.sendMessageListToConsole(TextUtils.convertStringListToComponentList(List.of(
                "    <dark_aqua>___  <aqua>_____",
                "   <dark_aqua>|___  <aqua>  |     <dark_aqua>ShowingItem <aqua>v1.0.0",
                "   <dark_aqua>    | <aqua>  |     <dark_gray>Author: Vovari2",
                "   <dark_aqua>|___| <aqua>__|__   <dark_gray>Status: " + (isEnable ? "<green>Enable" : "<red>Disable"), " ")));
    }

    @Override
    public void onDisable() {
        Text.clear();
        HandlerList.unregisterAll(this);
        Text.sendMessageToConsole("<red>Plugin disabled");
    }

    public static ShowingItem getInstance(){
        return instance;
    }
    public static boolean isEnable(){
        return instance.isEnable;
    }

    public static String getMatcher(){
        return instance.matcher;
    }
    public static Component getReplacement(){
        return instance.replacement;
    }
}
