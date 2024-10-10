package me.vovari2.showingitem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.ConsoleCommandSender;

import java.util.HashMap;
import java.util.List;

public class Text {
    public static final String PLUGIN_NAME = "ShowingItem";
    public static final String VERSION = "1.0.1";
    private static final Component MESSAGE_BEGIN = MiniMessage.miniMessage().deserialize("<dark_aqua>[<aqua>ShowingItem<dark_aqua>]<reset> ");

    private static ConsoleCommandSender sender;
    private static Text object;
    public static void initialize(ConsoleCommandSender newSender){
        sender = newSender;
    }
    public static void at(Text object){
        Text.object = object;
    }
    public static void clear(){
        object = null;
    }
    public static Component get(String key){
        return object.texts.get(key);
    }

    private final HashMap<String, Component> texts;
    public Text(){
        texts = new HashMap<>();
    }
    public void put(String key, Component text){
        texts.put(key, text);
    }


    public static void sendMessageListToConsole(List<Component> messages){
        for(Component message : messages)
            sendMessageToConsole(message, false);
    }

    public static void sendMessageToConsole(String message){
        sendMessageToConsole(message, true);
    }
    public static void sendMessageToConsole(String message, boolean hasMessageBegin){
        Component targetMessage = MiniMessage.miniMessage().deserialize(message);
        sender.sendMessage(hasMessageBegin ? MESSAGE_BEGIN.append(targetMessage) : targetMessage);
    }
    public static void sendMessageToConsole(Component message, boolean hasMessageBegin){
        sender.sendMessage(hasMessageBegin ? MESSAGE_BEGIN.append(message) : message);
    }
}
