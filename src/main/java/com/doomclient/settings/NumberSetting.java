package com.doomclient.settings;

/**
 * Numeric setting for integers.
 */
public class NumberSetting extends Setting<Double> {
    private double min;
    private double max;

    public NumberSetting(String name, String description, double defaultValue, double min, double max) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;

        // Clamp default value
        if (this.value < min) this.value = min;
        if (this.value > max) this.value = max;
    }

    @Override
    public void setValue(Double value) {
        // Clamp value
        if (value < min) {
            this.value = min;
        } else if (value > max) {
            this.value = max;
        } else {
            this.value = value;
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public int getValueAsInt() {
        return (int) Math.round(value);
    }
}
