package me.vovari2.showingitem.managers;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import me.vovari2.showingitem.commands.Executor;
import me.vovari2.showingitem.commands.TabCompleter;
import org.bukkit.command.PluginCommand;

public class CommandManager {
    public static void initialize(ShowingItem instance){
        PluginCommand command = instance.getCommand("showingitem");
        if (command != null){
            command.setExecutor(new Executor());
            command.setTabCompleter(new TabCompleter());
        }
        else Text.sendMessageToConsole("<red>Command /showingitem (/si) must not be null!");
    }
}
