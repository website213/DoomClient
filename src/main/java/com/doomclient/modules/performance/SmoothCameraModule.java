package com.doomclient.modules.performance;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * SmoothCamera - Makes camera movement smoother.
 */
public class SmoothCameraModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private float targetYaw;
    private float targetPitch;

    public SmoothCameraModule() {
        super("SmoothCamera", "Makes camera movement smoother", Category.PERFORMANCE);
    }

    @Override
    public void onEnable() {
        if (MC.player != null) {
            targetYaw = MC.player.getYaw();
            targetPitch = MC.player.getPitch();
        }
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (MC.player == null) return;

        float yaw = MC.player.getYaw();
        float pitch = MC.player.getPitch();

        // Smooth rotation
        MC.player.setYaw(yaw + (targetYaw - yaw) * 0.1f);
        MC.player.setPitch(pitch + (targetPitch - pitch) * 0.1f);
    }
}
