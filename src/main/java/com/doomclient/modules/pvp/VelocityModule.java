package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

/**
 * Velocity - Reduces knockback by moving opposite to knockback direction.
 */
public class VelocityModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting reduction;

    public VelocityModule() {
        super("Velocity", "Reduces knockback from attacks", Category.PVP);

        this.reduction = new NumberSetting("Reduction", "Knockback reduction %", 100.0, 0.0, 100.0);
        addSetting(reduction);
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

        Vec3d velocity = MC.player.getVelocity();
        double factor = 1.0 - (reduction.getValue() / 100.0);

        MC.player.setVelocity(
                velocity.x * factor,
                velocity.y,
                velocity.z * factor
        );
    }
}
