package me.vovari2.showingitem.commands.commands;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import me.vovari2.showingitem.commands.Command;
import me.vovari2.showingitem.exceptions.ComponentException;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends Command {
    public ReloadCommand(CommandSender sender, String[] args, ShowingItem plugin) {
        super(sender, args, plugin);
    }

    @Override
    public void execute() throws Exception {
        if (!sender.hasPermission("showingitem.*"))
            throw new ComponentException(Text.getComponent("warning.dont_have_permission"));

        if (args.length > 2)
            throw new ComponentException(Text.getComponent("warning.command_incorrectly"));

        plugin.onDisable();
        plugin.onEnable();

        Text.sendSenderInfoMessage(sender, Text.getComponent("command.reload"));
    }
}
