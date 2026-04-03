package com.doomclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumers;
import org.lwjgl.opengl.GL11;

/**
 * Utility functions for rendering.
 */
public class RenderUtils {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    /**
     * Draws a colored box around coordinates.
     */
    public static void drawBox(MatrixStack matrices, double x, double y, double z, double width, double height, double length, int color) {
        drawBox(matrices, x, y, z, width, height, length, color, color);
    }

    /**
     * Draws a colored box with outline and fill.
     */
    public static void drawBox(MatrixStack matrices, double x, double y, double z, double width, double height, double length, int fillColor, int outlineColor) {
        // Simple box drawing - implementation would depend on your render system
        // This is a placeholder
    }

    /**
     * Draws a line between two points.
     */
    public static void drawLine(MatrixStack matrices, Vec3d start, Vec3d end, int color, float width) {
        // Line drawing implementation
    }

    /**
     * Draws text at a position.
     */
    public static void drawText(MatrixStack matrices, String text, double x, double y, int color) {
        if (MC.textRenderer != null) {
            MC.textRenderer.draw(matrices, text, (float)x, (float)y, color);
        }
    }

    /**
     * Gets the screen center X.
     */
    public static int getScreenCenterX() {
        if (MC.getWindow() == null) return 0;
        return MC.getWindow().getScaledWidth() / 2;
    }

    /**
     * Gets the screen center Y.
     */
    public static int getScreenCenterY() {
        if (MC.getWindow() == null) return 0;
        return MC.getWindow().getScaledHeight() / 2;
    }

    /**
     * Gets screen width.
     */
    public static int getScreenWidth() {
        if (MC.getWindow() == null) return 0;
        return MC.getWindow().getScaledWidth();
    }

    /**
     * Gets screen height.
     */
    public static int getScreenHeight() {
        if (MC.getWindow() == null) return 0;
        return MC.getWindow().getScaledHeight();
    }

    /**
     * Converts RGB to integer color.
     */
    public static int color(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    /**
     * Converts RGB to integer color (no alpha).
     */
    public static int color(int r, int g, int b) {
        return color(r, g, b, 255);
    }
}
