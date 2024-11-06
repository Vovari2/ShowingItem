package me.vovari2.showingitem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1)
            return getPermissionedCommands(sender);
        return List.of();
    }

    private List<String> getPermissionedCommands(CommandSender sender){
        List<String> commands = Executor.getCommandList();
        commands.removeIf(command -> !sender.hasPermission("usual_counters." + command) && !command.equals("ignore"));
        return commands;
    }
}
