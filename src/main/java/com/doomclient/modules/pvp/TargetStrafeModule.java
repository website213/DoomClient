package com.doomclient.modules.pvp;

import com.doomclient.module.Module;
import com.doomclient.util.PlayerUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

/**
 * TargetStrafe - Circles around targets while attacking.
 */
public class TargetStrafeModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private Entity target;

    public TargetStrafeModule() {
        super("TargetStrafe", "Circles around targets while attacking", Category.PVP);
    }

    @Override
    public void onEnable() {
        target = null;
    }

    @Override
    public void onDisable() {
        target = null;
    }

    @Override
    public void onTick() {
        if (MC.player == null || MC.world == null) return;

        // Find nearest player
        for (Entity entity : MC.world.getEntities()) {
            if (entity instanceof PlayerEntity && entity != MC.player) {
                target = entity;
                break;
            }
        }

        if (target != null) {
            strafeAround(target);
        }
    }

    private void strafeAround(Entity target) {
        if (MC.player == null) return;

        Vec3d playerPos = MC.player.getPos();
        Vec3d targetPos = target.getPos();
        Vec3d direction = targetPos.subtract(playerPos).normalize();

        // Strafe perpendicular to target
        Vec3d strafeDir = new Vec3d(-direction.z, 0, direction.x).normalize();

        MC.player.addVelocity(strafeDir.x * 0.5, 0, strafeDir.z * 0.5);
    }
}
