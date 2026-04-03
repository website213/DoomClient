package com.doomclient.module;

import com.doomclient.settings.Setting;
import java.util.*;

/**
 * Abstract base class for all modules.
 * Modules are features that can be toggled on/off and support custom settings.
 */
public abstract class Module {
    protected String name;
    protected String description;
    protected Category category;
    protected boolean enabled;
    protected int keyCode; // 0 means no keybind

    protected List<Setting<?>> settings;

    public enum Category {
        PVP("PvP"),
        UTILITY("Utility"),
        PERFORMANCE("Performance"),
        RENDER("Render");

        public final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }
    }

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keyCode = 0;
        this.settings = new ArrayList<>();
    }

    /**
     * Called when the module is enabled.
     */
    public abstract void onEnable();

    /**
     * Called when the module is disabled.
     */
    public abstract void onDisable();

    /**
     * Called every game tick while the module is enabled.
     */
    public abstract void onTick();

    /**
     * Toggles the module on/off.
     */
    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }

    /**
     * Enables the module.
     */
    public void enable() {
        if (!enabled) {
            enabled = true;
            onEnable();
        }
    }

    /**
     * Disables the module.
     */
    public void disable() {
        if (enabled) {
            enabled = false;
            onDisable();
        }
    }

    /**
     * Adds a setting to this module.
     */
    protected <T> void addSetting(Setting<T> setting) {
        settings.add(setting);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            enable();
        } else {
            disable();
        }
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public List<Setting<?>> getSettings() {
        return new ArrayList<>(settings);
    }

    @Override
    public String toString() {
        return name + " [" + (enabled ? "ON" : "OFF") + "]";
    }
}
