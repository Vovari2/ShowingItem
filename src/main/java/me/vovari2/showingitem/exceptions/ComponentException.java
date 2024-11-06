package me.vovari2.showingitem.exceptions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ComponentException extends Exception{
    private final Component componentMessage;

    public ComponentException(String message) {
        super(message);
        componentMessage = MiniMessage.miniMessage().deserialize("<red>" + message);
    }
    public ComponentException(Component message) {
        super(MiniMessage.miniMessage().serialize(message));
        componentMessage = message;
    }

    public Component getComponentMessage(){
        return componentMessage;
    }
}
