package me.vovari2.showingitem.commands;

import me.vovari2.showingitem.ShowingItem;
import org.bukkit.command.CommandSender;

public abstract class Command {
    protected CommandSender sender;
    protected String[] args;
    protected ShowingItem plugin;

    public Command(CommandSender sender, String[] args, ShowingItem plugin){
        this.sender = sender;
        this.args = args;
        this.plugin = plugin;
    }

    public abstract void execute() throws Exception;
}
