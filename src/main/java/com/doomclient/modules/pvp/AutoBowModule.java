package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

/**
 * AutoBow - Automatically draws and shoots bows.
 */
public class AutoBowModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private int drawTime;

    public AutoBowModule() {
        super("AutoBow", "Automatically shoots bows", Category.PVP);
        this.drawTime = 0;
    }

    @Override
    public void onEnable() {
        drawTime = 0;
    }

    @Override
    public void onDisable() {
        // Release bow if drawn
        if (MC.player != null && MC.interactionManager != null) {
            MC.interactionManager.stopUsingItem(MC.player);
        }
    }

    @Override
    public void onTick() {
        if (MC.player == null || MC.interactionManager == null) return;

        ItemStack mainHand = MC.player.getMainHandStack();

        if (mainHand.getItem() instanceof BowItem) {
            if (drawTime < 20) {
                MC.interactionManager.interactItem(MC.player, Hand.MAIN_HAND);
                drawTime++;
            } else {
                MC.interactionManager.stopUsingItem(MC.player);
                drawTime = 0;
            }
        }
    }
}
