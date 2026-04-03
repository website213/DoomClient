package com.doomclient;

import com.doomclient.manager.ModuleManager;
import com.doomclient.manager.KeyBindManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the Doom Client mod.
 * Manages the lifecycle and coordination of all systems.
 */
public class DoomClient {
    public static final String MOD_ID = "doomclient";
    public static final String MOD_NAME = "Doom Client";
    public static final String MOD_VERSION = "1.0.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static DoomClient instance;
    private ModuleManager moduleManager;
    private KeyBindManager keyBindManager;

    private DoomClient() {
        this.moduleManager = new ModuleManager();
        this.keyBindManager = new KeyBindManager();
    }

    /**
     * Initializes the Doom Client. Called on client setup.
     */
    public static void init() {
        if (instance == null) {
            instance = new DoomClient();
            instance.moduleManager.registerAllModules();
            LOGGER.info("Doom Client initialized!");
        }
    }

    public static DoomClient getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public KeyBindManager getKeyBindManager() {
        return keyBindManager;
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
