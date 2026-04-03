package com.doomclient;

import net.fabricmc.api.ClientModInitializer;

/**
 * Main client initializer for Fabric modloader.
 */
public class DoomClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DoomClient.init();
    }
}
