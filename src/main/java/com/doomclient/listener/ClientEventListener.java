package com.doomclient.listener;

import com.doomclient.DoomClient;
import com.doomclient.DoomClientInitializer;
import com.doomclient.manager.ModuleManager;
import com.doomclient.gui.TouchGUI;
import net.minecraft.client.MinecraftClient;

/**
 * Handles client events like ticks and key presses.
 */
public class ClientEventListener {
    private static final MinecraftClient MC = MinecraftClient.getInstance();
    private TouchGUI gui = null;
    private long lastGuiToggle = 0;

    public void onClientTick(MinecraftClient client) {
        if (MC.player == null) return;

        // Tick all enabled modules
        DoomClient.getInstance().getModuleManager().tickAll();

        // Handle GUI toggle (semicolon key)
        if (DoomClientInitializer.guiKeyBinding.wasPressed()) {
            long now = System.currentTimeMillis();
            if (now - lastGuiToggle > 100) { // Debounce
                toggleGUI();
                lastGuiToggle = now;
            }
        }
    }

    private void toggleGUI() {
        if (gui == null) {
            gui = new TouchGUI();
        }
        gui.setVisible(!gui.isVisible());
        if (gui.isVisible()) {
            MC.setScreen(gui);
        } else {
            MC.setScreen(null);
        }
    }
}
