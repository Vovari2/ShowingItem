package me.vovari2.showingitem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    private final List<String> commands;
    public TabCompleter(){
        commands = List.of("help", "reload");
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1 && sender.hasPermission("showingitem.commands"))
            return commands;
        return List.of();
    }
}
