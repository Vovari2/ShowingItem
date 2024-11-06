package me.vovari2.showingitem;

import me.vovari2.showingitem.exceptions.ComponentException;
import me.vovari2.showingitem.utils.FileUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class Text {
    public static final String PLUGIN_NAME = ShowingItem.getInstance().getName();
    public static final String VERSION = ShowingItem.getInstance().getDescription().getVersion();
    private static final Component MESSAGE_BEGIN = MiniMessage.miniMessage().deserialize("<gray>[ShowingItem]<reset> ");

    private static ConsoleCommandSender sender;
    protected static void initialize(ConsoleCommandSender newSender) throws ComponentException {
        sender = newSender;
        new Text().initializeInside();
    }

    private FileConfiguration fileTexts;
    private void initializeInside() throws ComponentException {
        FileUtils.createPluginFileInDataFolder("text.yml");
        fileTexts = FileUtils.getYamlConfiguration("text.yml");

        texts = new HashMap<>();
        for (String key : fileTexts.getKeys(true))
            texts.put(key, getComponent(key));
    }
    private Component getComponent(String path) throws ComponentException{
        String value = fileTexts.getString(path);
        if (value == null)
            throw new ComponentException("Value " + path + " is null!");
        if (value.isEmpty())
            throw new ComponentException("Value " + path + " is empty!");
        if (value.contains("&") || value.contains("§"))
            throw new ComponentException("Value " + path + " must not have char \"&\" or \"§\"!");
        return MiniMessage.miniMessage().deserialize(value);
    }


    private static HashMap<String, Component> texts;
    public static Component get(String key){
        Component message = texts.get(key);
        if (message == null) {
            sendMessageToConsole("<red>Text value " + key + " does not exist!");
            return MiniMessage.miniMessage().deserialize(key);
        }
        return message;
    }
    public static Component replace(Component message, String matcher, Component replacement){
        TextReplacementConfig.Builder builder = TextReplacementConfig.builder();
        return message.replaceText(builder.matchLiteral(matcher).replacement(replacement).build());
    }
    public static Component replace(String key, String... values){
        if (values.length % 2 == 1)
            return Component.text(key);

        Component message = get(key);
        TextReplacementConfig.Builder builder = TextReplacementConfig.builder();
        for (int i = 0; i < values.length; i += 2)
            message = message.replaceText(builder.match(values[i]).replacement(values[i+1]).build());

        return message;
    }
    protected static void clear(){
        texts.clear();
    }


    public static void sendMessageToConsole(String message){
        sendMessageToConsole(message, true);
    }
    public static void sendMessageToConsole(String message, boolean hasMessageBegin){
        Component targetMessage = MiniMessage.miniMessage().deserialize(message);
        sender.sendMessage(hasMessageBegin ? MESSAGE_BEGIN.append(targetMessage) : targetMessage);
    }
    public static void sendMessageToConsole(Component message){
        sendMessageToConsole(message, true);
    }
    public static void sendMessageToConsole(Component message, boolean hasMessageBegin){
        sender.sendMessage(hasMessageBegin ? MESSAGE_BEGIN.append(message) : message);
    }

    public static void sendMessagesToConsole(String... messages){
        for (String message : messages)
            sendMessageToConsole(message, false);
    }
}
