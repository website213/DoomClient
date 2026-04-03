package com.doomclient.manager;

import java.util.*;

/**
 * Manages keybindings for modules.
 */
public class KeyBindManager {
    private Map<Integer, String> keyBinds; // keyCode -> moduleName

    public KeyBindManager() {
        this.keyBinds = new HashMap<>();
    }

    /**
     * Binds a key to a module.
     */
    public void bindKey(int keyCode, String moduleName) {
        keyBinds.put(keyCode, moduleName);
    }

    /**
     * Unbinds a key.
     */
    public void unbindKey(int keyCode) {
        keyBinds.remove(keyCode);
    }

    /**
     * Gets the module name for a key.
     */
    public String getModuleForKey(int keyCode) {
        return keyBinds.get(keyCode);
    }

    /**
     * Gets the key for a module.
     */
    public Integer getKeyForModule(String moduleName) {
        for (Map.Entry<Integer, String> entry : keyBinds.entrySet()) {
            if (entry.getValue().equals(moduleName)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Gets all key bindings.
     */
    public Map<Integer, String> getAllBindings() {
        return new HashMap<>(keyBinds);
    }
}
