package com.doomclient.manager;

import com.doomclient.module.Module;
import com.doomclient.module.Module.Category;
import com.doomclient.modules.pvp.*;
import com.doomclient.modules.utility.*;
import com.doomclient.modules.performance.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages all modules - registration, storage, and ticking.
 */
public class ModuleManager {
    private List<Module> modules;

    public ModuleManager() {
        this.modules = new ArrayList<>();
    }

    /**
     * Registers all available modules.
     */
    public void registerAllModules() {
        // PvP Modules
        register(new KillAuraModule());
        register(new AutoClickerModule());
        register(new CriticalsModule());
        register(new VelocityModule());
        register(new TargetStrafeModule());
        register(new AutoBowModule());
        register(new AutoPotModule());

        // Utility Modules
        register(new AutoSprintModule());
        register(new AutoMineModule());
        register(new FlightModule());
        register(new ElytraFlightModule());
        register(new XRayModule());
        register(new ESPModule());
        register(new ScaffoldModule());
        register(new InventoryTweaksModule());
        register(new SpeedModule());
        register(new NoFallModule());

        // Performance Modules
        register(new FPSBoostModule());
        register(new FastCraftModule());
        register(new ChunkPreloadModule());
        register(new AntiLagModule());
        register(new BetterAnimationsModule());
        register(new SmoothCameraModule());
    }

    /**
     * Registers a single module.
     */
    public void register(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    /**
     * Unregisters a module.
     */
    public void unregister(Module module) {
        modules.remove(module);
        if (module.isEnabled()) {
            module.disable();
        }
    }

    /**
     * Ticks all enabled modules.
     */
    public void tickAll() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }

    /**
     * Gets all modules.
     */
    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }

    /**
     * Gets modules by category.
     */
    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    /**
     * Gets a module by name.
     */
    public Module getModule(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Toggles a module by name.
     */
    public void toggleModule(String name) {
        Module module = getModule(name);
        if (module != null) {
            module.toggle();
        }
    }

    /**
     * Gets all enabled modules.
     */
    public List<Module> getEnabledModules() {
        return modules.stream()
                .filter(Module::isEnabled)
                .collect(Collectors.toList());
    }

    /**
     * Disables all modules.
     */
    public void disableAll() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.disable();
            }
        }
    }
}
