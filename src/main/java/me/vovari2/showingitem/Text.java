package me.vovari2.showingitem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Text {
    private static final Component startOfMessage = MiniMessage.miniMessage().deserialize("<dark_aqua>[<aqua>ShowingItem</aqua>]<reset> ");
    private static Text object;
    private final HashMap<String, Component> texts;
    public Text(HashMap<String, Component> texts){
        this.texts = texts;
    }

    public static void at(Text object){
        Text.object = object;
    }
    public static void clear(){
        object = null;
    }

    public static Component getComponent(String key){
        return object.texts.get(key);
    }




    public static void sendInfoMessage(String message){
        sendSenderInfoMessage(ShowingItem.getConsoleSender(), message);
    }

    public static void sendSenderInfoMessage(CommandSender sender, String message){
        sender.sendMessage(startOfMessage.append(MiniMessage.miniMessage().deserialize(message)));
    }
    public static void sendSenderInfoMessage(CommandSender sender, Component message){
        sender.sendMessage(startOfMessage.append(message));
    }

    public static void sendListMessages(List<Component> messages){
        for(Component message : messages)
            sendSenderMessage(ShowingItem.getConsoleSender(), message);
    }
    private static void sendSenderMessage(CommandSender sender, Component message){
        sender.sendMessage(message);
    }


    public static List<Component> convertStringListToComponentList(List<String> stringList){
        List<Component> listComponent = new ArrayList<>();
        for (String string : stringList)
            listComponent.add(MiniMessage.miniMessage().deserialize(string));
        return listComponent;
    }
}
