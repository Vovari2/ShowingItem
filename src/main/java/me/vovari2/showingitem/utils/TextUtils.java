package me.vovari2.showingitem.utils;

import me.vovari2.showingitem.Text;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.HashMap;

public class TextUtils {
    public static void loadTexts(){
        HashMap<String, Component> texts = new HashMap<>();
        texts.put("command.help", convertTo("<gradient:#4C3BCF:#4B70F5:#4C3BCF><strikethrough>      </strikethrough> ShowingItem Helper <strikethrough>      </strikethrough></gradient><newline><newline>" +
                "    <gradient:#7695FF:#9DBDFF>- <hover:show_text:\"<gray>View information about commands\">/si help</hover></gradient><newline>" +
                "    <gradient:#7695FF:#9DBDFF>- <hover:show_text:\"<gray>Reload plugin\">/si reload</hover></gradient><newline>"));
        texts.put("command.reload", convertTo("<gradient:#54B435:#82CD47>Plugin reloaded!</gradient>"));
        texts.put("warning.command_incorrectly", convertTo("<red>Command is incorrectly! (/uc help)"));
        texts.put("warning.dont_have_permission", convertTo("<red>You don't have permissions!"));
        texts.put("warning.you_not_player", convertTo("<red>You are not a player!"));
        Text.at(new Text(texts));
    }
    private static Component convertTo(String text){
        return MiniMessage.miniMessage().deserialize(text);
    }
}
