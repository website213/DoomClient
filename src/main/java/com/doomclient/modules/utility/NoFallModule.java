package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * NoFall - Prevents fall damage.
 */
public class NoFallModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public NoFallModule() {
        super("NoFall", "Prevents fall damage", Category.UTILITY);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (MC.player == null) return;

        // Reset fall distance to prevent damage
        if (MC.player.fallDistance > 3.0f) {
            MC.player.fallDistance = 0.0f;
        }
    }
}
