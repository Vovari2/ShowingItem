package me.vovari2.showingitem.commands;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import me.vovari2.showingitem.commands.commands.HelpCommand;
import me.vovari2.showingitem.commands.commands.ReloadCommand;
import me.vovari2.showingitem.exceptions.ComponentException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Executor implements CommandExecutor {
    private static HashMap<String, Class<? extends Command>> commands;
    public static List<String> getCommandList(){
        return new LinkedList<>(commands.keySet());
    }

    public Executor(){
        commands = new HashMap<>();
        commands.put("help", HelpCommand.class);
        commands.put("reload", ReloadCommand.class);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command commandObject, @NotNull String label, @NotNull String[] args) {
        try{
            if (args.length == 0){
                executeCommand("help", sender, args);
                return true;
            }

            String str = args[0].toLowerCase();
            if (!commands.containsKey(str))
                throw new ComponentException(Text.get("warning.command_incorrectly"));

            if (!ShowingItem.isEnable() && !(args.length == 1 && args[0].equals("reload")))
                throw new ComponentException(Text.get("warning.command_incorrectly"));

            executeCommand(args[0].toLowerCase(), sender, args);
        }
        catch (ComponentException error){
            sender.sendMessage(error.getComponentMessage());
        }
        catch (Exception error){
            error.printStackTrace();
            sender.sendMessage(error.getMessage());
        }
        return true;
    }

    private void executeCommand(String subcommand, CommandSender sender, String[] args) throws Exception{
        Command command = commands.get(subcommand).getDeclaredConstructor(CommandSender.class, String[].class, ShowingItem.class).newInstance(sender, args, ShowingItem.getInstance());
        command.execute();
    }
}
