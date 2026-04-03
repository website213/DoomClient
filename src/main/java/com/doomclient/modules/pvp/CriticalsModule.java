package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * Criticals - Makes all hits critical hits.
 */
public class CriticalsModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public CriticalsModule() {
        super("Criticals", "Makes attacks always critical", Category.PVP);
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

        // Jump and fall to trigger critical hit
        if (MC.player.isOnGround() && !MC.player.isFallFlying()) {
            MC.player.jump();
        }
    }
}
