package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

/**
 * Speed - Increases movement speed.
 */
public class SpeedModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting multiplier;

    public SpeedModule() {
        super("Speed", "Increases movement speed", Category.UTILITY);

        this.multiplier = new NumberSetting("Multiplier", "Speed multiplier", 1.5, 1.0, 3.0);
        addSetting(multiplier);
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

        MC.player.setVelocity(
                MC.player.getVelocity().x * multiplier.getValue(),
                MC.player.getVelocity().y,
                MC.player.getVelocity().z * multiplier.getValue()
        );
    }
}
