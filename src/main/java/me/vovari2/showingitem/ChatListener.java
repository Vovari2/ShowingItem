package me.vovari2.showingitem;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncChat(AsyncChatEvent event){
        if (!ShowingItem.isEnable())
            return;

        Player player = event.getPlayer();
        if (!player.hasPermission("showingitem.use") && !player.hasPermission("showingitem.*"))
            return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.isEmpty())
            return;

        event.message(
                replace(event.originalMessage(),
                        ShowingItem.getMatcher(),
                        replace(ShowingItem.getReplacement(),
                                "<%item%>",
                                getComponentFromItemStack(itemStack))));
    }
    private Component replace(Component message, String matcher, Component replacement){
        return message.replaceText(TextReplacementConfig.builder().matchLiteral(matcher).replacement(replacement).build());
    }

    private Component getComponentFromItemStack(ItemStack itemStack){
        return ((Component) ((TranslatableComponent) itemStack.displayName()).arguments().getFirst().value()).hoverEvent(itemStack.asHoverEvent());
    }
}
