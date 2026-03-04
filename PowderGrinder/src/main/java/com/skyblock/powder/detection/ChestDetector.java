package com.skyblock.powder.detection;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.phys.Vec3;
import com.skyblock.powder.PowderGrinderMod;
import com.skyblock.powder.config.ModConfig;

import java.*;
import java.util.*;

public class ChestDetector {
    
    private final Minecraft client = Minecraft.getInstance();
    private final ModConfig config = PowderGrinderMod.getConfig();
    private final Set<BlockPos> nearbyChests = new HashSet<>();
    private long lastScanTime = 0;
    private static final long SCAN_COOLDOWN = 500; // 500ms between scans
    
    private static final Set<String> CHEST_BLOCKS = new HashSet<>(Arrays.asList(
        "chest",
        "trapped_chest",
        "ender_chest"
    ));
    
    public void update() {
        if (!config.isChestDetectionEnabled()) {
            return;
        }
        
        if (System.currentTimeMillis() - lastScanTime < SCAN_COOLDOWN) {
            return;
        }
        
        lastScanTime = System.currentTimeMillis();
        scanForChests();
    }
    
    private void scanForChests() {
        nearbyChests.clear();
        
        if (client.player == null || client.level == null) {
            return;
        }
        
        BlockPos playerPos = client.player.blockPosition();
        int scanDistance = config.getChestScanDistance();
        
        for (int x = -scanDistance; x <= scanDistance; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -scanDistance; z <= scanDistance; z++) {
                    BlockPos checkPos = playerPos.offset(x, y, z);
                    
                    if (isChest(client.level, checkPos)) {
                        nearbyChests.add(checkPos);
                    }
                }
            }
        }
    }
    
    private boolean isChest(Level level, BlockPos pos) {
        String blockName = level.getBlockState(pos).getBlock().getName().getString();
        return CHEST_BLOCKS.stream().anyMatch(blockName::contains);
    }
    
    public Set<BlockPos> getNearbyChests() {
        return new HashSet<>(nearbyChests);
    }
    
    public BlockPos getNearestChest() {
        if (nearbyChests.isEmpty() || client.player == null) {
            return null;
        }
        
        BlockPos playerPos = client.player.blockPosition();
        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        
        for (BlockPos chest : nearbyChests) {
            double distance = playerPos.distSqr(chest);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = chest;
            }
        }
        
        return nearest;
    }
    
    public void openNearestChest() {
        if (!config.isAutoOpenChests() || client.player == null || client.gameMode == null) {
            return;
        }
        
        BlockPos nearestChest = getNearestChest();
        if (nearestChest == null) {
            return;
        }
        
        // Face the chest
        Vec3 chestCenter = Vec3.atCenterOf(nearestChest);
        Vec3 playerEyes = client.player.getEyePosition(1.0f);
        Vec3 direction = chestCenter.subtract(playerEyes).normalize();
        
        double dx = direction.x;
        double dy = direction.y;
        double dz = direction.z;
        
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        
        float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
        float pitch = (float) Math.toDegrees(-Math.atan2(dy, horizontalDistance));
        
        client.player.setXRot(Math.max(-90.0f, Math.min(90.0f, pitch)));
        client.player.setYRot(yaw);
        
        // Open chest (interact)
        client.gameMode.useItemOn(client.player, client.player.containerMenu, 
            net.minecraft.world.phys.BlockHitResult.miss(playerEyes, 
            net.minecraft.world.phys.Vec3.ZERO, nearestChest, false));
    }
}
