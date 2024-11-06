package me.vovari2.showingitem;

import me.vovari2.showingitem.exceptions.ComponentException;
import me.vovari2.showingitem.managers.CommandManager;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowingItem extends JavaPlugin {
    private static ShowingItem instance;
    private boolean isEnable;

    @Override
    public void onEnable() {
        instance = this;

        try {
            Text.initialize(getServer().getConsoleSender());
            Config.initialize();
            isEnable = Config.ENABLED;

            CommandManager.initialize(instance);
            getServer().getPluginManager().registerEvents(new ChatListener(), this);
        }
        catch(ComponentException e){Text.sendMessageToConsole(e.getComponentMessage());isEnable = false;}

        Text.sendMessagesToConsole(
                "    <dark_aqua>___  <aqua>_____",
                "   <dark_aqua>|___  <aqua>  |     <dark_aqua>ShowingItem <aqua>v1.0.0",
                "   <dark_aqua>    | <aqua>  |     <dark_gray>Author: Vovari2",
                "   <dark_aqua>|___| <aqua>__|__   <dark_gray>Status: " + (isEnable ? "<green>Enable" : "<red>Disable"), " ");
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
}
