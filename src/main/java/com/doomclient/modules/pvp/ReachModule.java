package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;

public class ReachModule extends Module {
    public NumberSetting reach = new NumberSetting("Reach", "Increase block/entity reach distance", 3.0, 3.0, 10.0);

    public ReachModule() {
        super("Reach", "Increases reach distance for breaking blocks and attacking entities", Category.PVP, 82); // R key
        this.settings.add(reach);
    }

    @Override
    public void onEnable() {
        // Reach module enabled
    }

    @Override
    public void onDisable() {
        // Reach module disabled
    }

    @Override
    public void onTick() {
        // Reach is typically handled through event listeners or mixins
        // This is a placeholder for the tick logic
    }
}
