package com.doomclient.modules.performance;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * BetterAnimations - Improves animation rendering.
 */
public class BetterAnimationsModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public BetterAnimationsModule() {
        super("BetterAnimations", "Improves animation performance", Category.PERFORMANCE);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // Better animation handling
    }
}
