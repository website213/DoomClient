package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * AutoSprint - Automatically sprints when moving.
 */
public class AutoSprintModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public AutoSprintModule() {
        super("AutoSprint", "Automatically sprints while moving", Category.UTILITY);
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

        if (MC.player.input.movementForward > 0) {
            MC.player.setSprinting(true);
        } else {
            MC.player.setSprinting(false);
        }
    }
}
