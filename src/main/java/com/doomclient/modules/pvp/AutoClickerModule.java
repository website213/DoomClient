package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

/**
 * AutoClicker - Automatically clicks the mouse.
 */
public class AutoClickerModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting cps;
    private long lastClick;

    public AutoClickerModule() {
        super("AutoClicker", "Automatically clicks the mouse", Category.PVP);

        this.cps = new NumberSetting("CPS", "Clicks per second", 10.0, 1.0, 20.0);
        addSetting(cps);

        this.lastClick = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
        lastClick = System.currentTimeMillis();
    }

    @Override
    public void onDisable() {
        // Cleanup
    }

    @Override
    public void onTick() {
        if (MC.mouse == null) return;

        long now = System.currentTimeMillis();
        long delayMs = (long) (1000.0 / cps.getValue());

        if (now - lastClick >= delayMs) {
            MC.mouse.onMouseButton(-1, 0, 0);
            lastClick = now;
        }
    }
}
