package me.vovari2.showingitem.managers;

import me.vovari2.showingitem.ShowingItem;
import me.vovari2.showingitem.Text;
import me.vovari2.showingitem.exceptions.ComponentException;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultManager {
    private static boolean isVault;
    public static Economy getEconomy(ShowingItem instance) throws ComponentException {
        isVault = false;

        Text.sendMessagesToConsole("work 1 %s".formatted(instance.getServer().getPluginManager().getPlugin("Vault") == null));
        if (instance.getServer().getPluginManager().getPlugin("Vault") == null){
            Text.sendMessageToConsole(Text.get("vault.plugin_not_connected"));
            return null;
        }


        RegisteredServiceProvider<Economy> rsp = instance.getServer().getServicesManager().getRegistration(Economy.class);
        Text.sendMessagesToConsole("work 2 %s".formatted(rsp == null) );
        if (rsp == null){
            Text.sendMessageToConsole(Text.get("vault.plugin_not_connected"));
            return null;
        }

        isVault = true;
        return rsp.getProvider();
    }

    public static int getBalance(String playerName){
        return (int) ShowingItem.getEconomy().getBalance(Bukkit.getOfflinePlayer(playerName));
    }
    public static void takeBalance(String playerName, double money){
        ShowingItem.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(playerName), money);
    }

    public static boolean isVault(){
        return isVault;
    }
}
