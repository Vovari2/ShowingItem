package me.vovari2.showingitem;

import me.vovari2.showingitem.exceptions.ComponentException;
import me.vovari2.showingitem.utils.FileUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static boolean ENABLED;

    public static String MATCHER;
    public static Component REPLACEMENT;

    public static boolean VAULT_USED;
    public static int VAULT_COST;

    public static void initialize() throws ComponentException {
        new Config().initializeInside();
    }
    private FileConfiguration fileConfig;
    private void initializeInside() throws ComponentException {
        FileUtils.createPluginFileInDataFolder("config.yml");
        fileConfig = FileUtils.getYamlConfiguration("config.yml");

        ENABLED = getBoolean("enable");

        MATCHER = getString("matcher");
        REPLACEMENT = getComponent("replacement");

        VAULT_USED = getBoolean("vault.use_vault");
        VAULT_COST = getPositiveInt("vault.cost");
    }

    private boolean getBoolean(String path) throws ComponentException{
        Object object = fileConfig.get(path);
        if (!(object instanceof Boolean))
            throw new ComponentException("Value " + path + " is not a boolean!");
        return (boolean) object;
    }
    private Component getComponent(String path) throws ComponentException{
        String value = getString(path);
        if (value.isEmpty())
            throw new ComponentException("Value " + path + " is empty!");
        if (value.contains("&") || value.contains("§"))
            throw new ComponentException("Value " + path + " must not have char \"&\" or \"§\"!");
        return MiniMessage.miniMessage().deserialize(value);
    }
    private String getString(String path) throws ComponentException{
        Object object = fileConfig.get(path);
        if (!(object instanceof String ))
            throw new ComponentException("Value " + path + " is not an string!");
        return (String) object;
    }
    private int getPositiveInt(String path) throws ComponentException{
        int value = getInt(path);
        if (value <= 0)
            throw new ComponentException("Value " + path + " must be greater than 0!");
        return value;
    }
    private int getInt(String path) throws ComponentException{
        Object object = fileConfig.get(path);
        if (!(object instanceof Integer))
            throw new ComponentException("Value " + path + " is not an integer!");
        return (int) object;
    }

}
