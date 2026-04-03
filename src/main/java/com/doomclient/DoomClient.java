package com.doomclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the Doom Client mod.
 */
public class DoomClient {
    public static final String MOD_ID = "doomclient";
    public static final String MOD_NAME = "Doom Client";
    public static final String MOD_VERSION = "1.0.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    private static DoomClient instance;

    private DoomClient() {}

    public static void init() {
        if (instance == null) {
            instance = new DoomClient();
            LOGGER.info("Doom Client initialized!");
        }
    }

    public static DoomClient getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }
}
