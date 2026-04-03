package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

/**
 * Flight - Allows flying in survival mode.
 */
public class FlightModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting speed;

    public FlightModule() {
        super("Flight", "Allows flying in survival mode", Category.UTILITY);

        this.speed = new NumberSetting("Speed", "Flight speed", 0.5, 0.1, 2.0);
        addSetting(speed);
    }

    @Override
    public void onEnable() {
        if (MC.player != null) {
            MC.player.getAbilities().flying = true;
            MC.player.getAbilities().setFlySpeed((float) (speed.getValue() * 0.05f));
        }
    }

    @Override
    public void onDisable() {
        if (MC.player != null) {
            MC.player.getAbilities().flying = false;
        }
    }

    @Override
    public void onTick() {
        if (MC.player != null) {
            MC.player.getAbilities().setFlySpeed((float) (speed.getValue() * 0.05f));
        }
    }
}
