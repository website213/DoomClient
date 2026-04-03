package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

/**
 * AutoMine - Automatically mines blocks.
 */
public class AutoMineModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public AutoMineModule() {
        super("AutoMine", "Automatically mines blocks", Category.UTILITY);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (MC.interactionManager != null && MC.player != null) {
            MC.interactionManager.cancelBlockBreaking();
        }
    }

    @Override
    public void onTick() {
        if (MC.interactionManager == null || MC.player == null) return;

        // Left click to attack (mine)
        MC.interactionManager.attackBlock(MC.player.getBlockPos().offset(MC.player.getHorizontalFacing()), MC.player.getHorizontalFacing());
        MC.player.swingHand(Hand.MAIN_HAND);
    }
}
