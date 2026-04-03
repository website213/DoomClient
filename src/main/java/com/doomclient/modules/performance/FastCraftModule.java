package com.doomclient.modules.performance;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * FastCraft - Optimizes rendering and world loading.
 */
public class FastCraftModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public FastCraftModule() {
        super("FastCraft", "Optimizes rendering performance", Category.PERFORMANCE);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // Optimize rendering
    }
}
