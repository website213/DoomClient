package com.doomclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

/**
 * Main client initializer for Fabric modloader.
 */
public class DoomClientInitializer implements ClientModInitializer {

    public static KeyBinding guiKeyBinding;

    @Override
    public void onInitializeClient() {
        // Initialize the main client
        DoomClient.init();

        // Register GUI keybinding (semicolon key)
        guiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.doomclient.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_SEMICOLON,
                "category.doomclient"
        ));

        // Register event listeners
        ClientEventListener listener = new ClientEventListener();
        ClientTickEvents.START.register(listener::onClientTick);
    }
}
