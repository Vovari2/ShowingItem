package me.vovari2.showingitem.commands.commands;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import me.vovari2.showingitem.commands.Command;
import me.vovari2.showingitem.exceptions.ComponentException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand extends Command {
    public HelpCommand(CommandSender sender, String[] args, ShowingItem plugin) {
        super(sender, args, plugin);
    }

    @Override
    public void execute() throws Exception {
        if (!sender.hasPermission("showingitem.*"))
            throw new ComponentException(Text.getComponent("warning.dont_have_permission"));

        if (args.length > 1)
            throw new ComponentException(Text.getComponent("warning.command_incorrectly"));

        if (!(sender instanceof Player player))
            throw new ComponentException(Text.getComponent("warning.you_not_player"));

        player.sendMessage(Text.getComponent("command.help"));
    }
}
