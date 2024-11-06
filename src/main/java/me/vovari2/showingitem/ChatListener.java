package me.vovari2.showingitem;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.vovari2.showingitem.managers.VaultManager;
import me.vovari2.showingitem.utils.TextUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncChat(AsyncChatEvent event){
        if (!ShowingItem.isEnable())
            return;

        Player player = event.getPlayer();
        if (!player.hasPermission("showing_item.use"))
            return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getType().isAir())
            return;

        Component message = event.originalMessage();
        if (!PlainTextComponentSerializer.plainText().serialize(message).contains(Config.MATCHER))
            return;

        String playerName = player.getName();
        if (VaultManager.isVault() && Config.VAULT_USED){
            if (Config.VAULT_COST > VaultManager.getBalance(playerName)){
                player.sendMessage(Text.replace("vault.not_enough_coins_on_balance",
                        "<%matcher%>", Config.MATCHER,
                        "<%cost%>", String.valueOf(Config.VAULT_COST),
                        "<%balance%>", String.valueOf(VaultManager.getBalance(playerName))));
                event.setCancelled(true);
                return;
            }

            player.sendMessage(Text.replace("vault.use_feature",
                    "<%matcher%>", Config.MATCHER,
                    "<%cost%>", String.valueOf(Config.VAULT_COST),
                    "<%balance%>", String.valueOf(VaultManager.getBalance(playerName))));
            VaultManager.takeBalance(playerName, Config.VAULT_COST);
        }

        event.message(Text.replace(message,
                        Config.MATCHER,
                        Text.replace(Config.REPLACEMENT,
                                "<%item%>",
                                TextUtils.toComponent(itemStack))));
    }
}
