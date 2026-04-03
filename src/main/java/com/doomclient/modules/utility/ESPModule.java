package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import com.doomclient.util.Color;
import com.doomclient.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.ItemEntity;

/**
 * ESP - Highlights players, mobs, and items.
 */
public class ESPModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private BooleanSetting players;
    private BooleanSetting mobs;
    private BooleanSetting items;

    public ESPModule() {
        super("ESP", "Highlights entities through walls", Category.UTILITY);

        this.players = new BooleanSetting("Players", "Highlight players", true);
        this.mobs = new BooleanSetting("Mobs", "Highlight mobs", true);
        this.items = new BooleanSetting("Items", "Highlight items", false);

        addSetting(players);
        addSetting(mobs);
        addSetting(items);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (MC.world == null) return;

        for (Entity entity : MC.world.getEntities()) {
            if (shouldHighlight(entity)) {
                // Highlight entity - would need render hook
            }
        }
    }

    private boolean shouldHighlight(Entity entity) {
        if (entity == MC.player) return false;

        if (entity instanceof PlayerEntity) return players.getValue();
        if (entity instanceof MobEntity) return mobs.getValue();
        if (entity instanceof ItemEntity) return items.getValue();

        return false;
    }
}
