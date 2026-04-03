package com.doomclient.settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Mode/String setting with predefined options.
 */
public class ModeSetting extends Setting<String> {
    private List<String> options;

    public ModeSetting(String name, String description, String defaultValue, String... options) {
        super(name, description, defaultValue);
        this.options = new ArrayList<>();
        for (String option : options) {
            this.options.add(option);
        }

        // Ensure default value is in options
        if (!this.options.contains(defaultValue)) {
            this.options.add(defaultValue);
        }
    }

    @Override
    public void setValue(String value) {
        if (options.contains(value)) {
            this.value = value;
        }
    }

    public List<String> getOptions() {
        return new ArrayList<>(options);
    }

    public int getCurrentIndex() {
        return options.indexOf(value);
    }

    public void setIndex(int index) {
        if (index >= 0 && index < options.size()) {
            this.value = options.get(index);
        }
    }
}
