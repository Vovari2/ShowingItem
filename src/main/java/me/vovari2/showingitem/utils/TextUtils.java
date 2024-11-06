package me.vovari2.showingitem.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.inventory.ItemStack;

public class TextUtils {
    public static Component toComponent(ItemStack itemStack){
        return (((TranslatableComponent) itemStack.displayName()).args().get(0)).hoverEvent(itemStack.asHoverEvent());
    }
}
