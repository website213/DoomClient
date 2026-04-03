package com.doomclient.modules.utility;

import com.doomclient.module.Module;
import com.doomclient.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * Scaffold - Automatically places blocks under you.
 */
public class ScaffoldModule extends Module {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private NumberSetting range;

    public ScaffoldModule() {
        super("Scaffold", "Automatically places blocks under you", Category.UTILITY);

        this.range = new NumberSetting("Range", "Block placement range", 4.5, 1.0, 6.0);
        addSetting(range);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (MC.player == null || MC.interactionManager == null) return;

        // Find a block below the player
        BlockPos below = MC.player.getBlockPos().down();

        // If air, place a block
        if (MC.world != null && MC.world.getBlockState(below).isAir()) {
            // Find blocks in inventory
            for (int i = 0; i < MC.player.getInventory().size(); i++) {
                ItemStack stack = MC.player.getInventory().getStack(i);
                if (stack.getItem() == Items.DIRT || stack.getItem() == Items.COBBLESTONE ||
                    stack.getItem() == Items.STONE) {
                    MC.player.getInventory().selectedSlot = i;
                    MC.interactionManager.interactBlock(MC.player, Hand.MAIN_HAND,
                            MC.world.raycast(MC.player.getCameraPosVec(1.0f), 
                                    MC.player.getCameraPosVec(1.0f).add(MC.player.getRotationVec(1.0f).multiply(5.0))).getBlockPos(),
                            Direction.UP);
                    return;
                }
            }
        }
    }
}
