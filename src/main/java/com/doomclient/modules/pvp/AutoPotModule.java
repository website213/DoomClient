package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

/**
 * AutoPot - Automatically drinks health potions when low on health.
 */
public class AutoPotModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public AutoPotModule() {
        super("AutoPot", "Automatically drinks potions", Category.PVP);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (MC.player == null || MC.interactionManager == null) return;

        if (MC.player.getHealth() < 8.0) {
            // Find health potion
            for (int i = 0; i < MC.player.getInventory().size(); i++) {
                ItemStack stack = MC.player.getInventory().getStack(i);
                if (stack.isOf(Items.POTION)) {
                    MC.player.getInventory().selectedSlot = i;
                    MC.interactionManager.interactItem(MC.player, Hand.MAIN_HAND);
                    return;
                }
            }
        }
    }
}
