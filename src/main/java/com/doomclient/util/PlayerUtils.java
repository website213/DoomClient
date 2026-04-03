package com.doomclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;

/**
 * Utility functions for player-related operations.
 */
public class PlayerUtils {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public static boolean isMoving() {
        if (MC.player == null) return false;
        return MC.player.getVelocity().length() > 0.01;
    }

    public static boolean isInAir() {
        if (MC.player == null) return false;
        return !MC.player.isOnGround();
    }

    public static double getDistance(double x, double y, double z) {
        if (MC.player == null) return 0;
        return MC.player.getPos().distanceTo(new Vec3d(x, y, z));
    }

    public static double getEyeHeight() {
        if (MC.player == null) return 0;
        return MC.player.getEyeY();
    }

    public static Vec3d getEyePos() {
        if (MC.player == null) return Vec3d.ZERO;
        return new Vec3d(MC.player.getX(), MC.player.getEyeY(), MC.player.getZ());
    }

    public static boolean isAlive() {
        if (MC.player == null) return false;
        return MC.player.getHealth() > 0;
    }

    public static float getHealth() {
        if (MC.player == null) return 0;
        return MC.player.getHealth();
    }

    public static void jump() {
        if (MC.player != null) {
            MC.player.jump();
        }
    }

    public static void setYaw(float yaw) {
        if (MC.player != null) {
            MC.player.setYaw(yaw);
        }
    }

    public static void setPitch(float pitch) {
        if (MC.player != null) {
            MC.player.setPitch(pitch);
        }
    }

    public static float getYaw() {
        if (MC.player == null) return 0;
        return MC.player.getYaw();
    }

    public static float getPitch() {
        if (MC.player == null) return 0;
        return MC.player.getPitch();
    }
}
