package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

/**
 * ElytraFlight - Flight using elytra.
 */
public class ElytraFlightModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting speed;

    public ElytraFlightModule() {
        super("ElytraFlight", "Advanced elytra flight controls", Category.UTILITY);

        this.speed = new NumberSetting("Speed", "Flight speed boost", 1.0, 0.5, 3.0);
        addSetting(speed);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (MC.player == null || !MC.player.isFallFlying()) return;

        Vec3d velocity = MC.player.getVelocity();
        Vec3d boosted = velocity.multiply(speed.getValue());

        MC.player.setVelocity(boosted);
    }
}
