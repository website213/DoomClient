package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import com.doomclient.settings.BooleanSetting;
import com.doomclient.util.PlayerUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

/**
 * KillAura - Automatically attacks nearby entities.
 */
public class KillAuraModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting range;
    private NumberSetting attackSpeed;
    private BooleanSetting targetPlayers;
    private BooleanSetting targetMobs;
    private BooleanSetting targetAnimals;

    public KillAuraModule() {
        super("KillAura", "Automatically attacks nearby entities", Category.PVP);

        this.range = new NumberSetting("Range", "Attack range", 4.5, 1.0, 8.0);
        this.attackSpeed = new NumberSetting("Speed", "Attacks per second", 20.0, 1.0, 60.0);
        this.targetPlayers = new BooleanSetting("Players", "Target players", true);
        this.targetMobs = new BooleanSetting("Mobs", "Target mobs", true);
        this.targetAnimals = new BooleanSetting("Animals", "Target animals", false);

        addSetting(range);
        addSetting(attackSpeed);
        addSetting(targetPlayers);
        addSetting(targetMobs);
        addSetting(targetAnimals);
    }

    @Override
    public void onEnable() {
        // Logic to initialize when enabled
    }

    @Override
    public void onDisable() {
        // Logic to clean up when disabled
    }

    @Override
    public void onTick() {
        if (MC.player == null || MC.world == null) return;

        for (Entity entity : MC.world.getEntities()) {
            if (shouldAttack(entity)) {
                attackEntity((LivingEntity) entity);
            }
        }
    }

    private boolean shouldAttack(Entity entity) {
        if (entity == MC.player || !(entity instanceof LivingEntity)) return false;

        double distance = PlayerUtils.getDistance(entity.getX(), entity.getY(), entity.getZ());
        if (distance > range.getValue()) return false;

        if (entity instanceof PlayerEntity) {
            return targetPlayers.getValue();
        }

        return targetMobs.getValue() || targetAnimals.getValue();
    }

    private void attackEntity(LivingEntity entity) {
        MC.interactionManager.attackEntity(MC.player, entity);
        MC.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
    }
}
