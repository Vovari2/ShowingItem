package me.vovari2.showingitem;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.vovari2.showingitem.utils.TextUtils;
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

        event.message(Text.replace(event.originalMessage(),
                        Config.MATCHER,
                        Text.replace(Config.REPLACEMENT,
                                "<%item%>",
                                TextUtils.toComponent(itemStack))));
    }
}
