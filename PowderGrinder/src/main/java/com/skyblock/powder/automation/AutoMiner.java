package com.skyblock.powder.automation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import com.skyblock.powder.PowderGrinderMod;
import com.skyblock.powder.config.ModConfig;

import java.util.*;

public class AutoMiner {
    
    private final Minecraft client = Minecraft.getInstance();
    private boolean isActive = false;
    private BlockPos currentTarget = null;
    private int miningTicks = 0;
    private float blockDamage = 0.0f;
    private final ModConfig config = PowderGrinderMod.getConfig();
    
    // Block whitelist for Skyblock powder grinding
    private static final Set<String> MINABLE_BLOCKS = new HashSet<>(Arrays.asList(
        "hard_stone",
        "minecraft:stone",
        "minecraft:deepslate",
        "skyblock:hard_stone"
    ));
    
    public void update() {
        if (!config.isAutoMiningEnabled()) {
            return;
        }
        
        LocalPlayer player = client.player;
        Level level = client.level;
        
        if (player == null || level == null) {
            return;
        }
        
        // Find nearest hard stone block if no target
        if (currentTarget == null || !isValidTarget(currentTarget)) {
            currentTarget = findNearestHardStone(player);
            blockDamage = 0.0f;
            miningTicks = 0;
        }
        
        if (currentTarget != null) {
            mineBlock(player, currentTarget);
        }
    }
    
    private void mineBlock(LocalPlayer player, BlockPos target) {
        Vec3 blockCenter = Vec3.atCenterOf(target);
        Vec3 playerPos = player.getEyePosition(1.0f);
        
        // Auto rotate to face the block if enabled
        if (config.isAutoRotate()) {
            rotateTowards(player, blockCenter);
        }
        
        // Check if in range
        double distance = playerPos.distanceTo(blockCenter);
        if (distance > config.getMaxMiningDistance()) {
            currentTarget = null;
            return;
        }
        
        // Start mining
        miningTicks++;
        blockDamage += 0.1f * config.getMiningSpeed();
        
        // Send attack/mining packet
        if (client.hitResult != null && client.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) client.hitResult;
            if (blockHit.getBlockPos().equals(target)) {
                client.gameMode.continueDestroyBlock();
            }
        }
        
        // Complete mining when block is fully damaged
        if (blockDamage >= 1.0f || miningTicks > 300) {
            if (client.gameMode != null) {
                client.gameMode.destroyBlock(target);
            }
            currentTarget = null;
            blockDamage = 0.0f;
            miningTicks = 0;
        }
    }
    
    private BlockPos findNearestHardStone(LocalPlayer player) {
        Level level = client.level;
        if (level == null) return null;
        
        BlockPos playerPos = player.blockPosition();
        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        
        int searchRadius = config.getMaxMiningDistance();
        
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -2; y <= searchRadius; y++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    BlockPos checkPos = playerPos.offset(x, y, z);
                    
                    if (isHardStone(level, checkPos)) {
                        double distance = playerPos.distSqr(checkPos);
                        if (distance < nearestDistance) {
                            nearestDistance = distance;
                            nearest = checkPos;
                        }
                    }
                }
            }
        }
        
        return nearest;
    }
    
    private boolean isHardStone(Level level, BlockPos pos) {
        String blockName = level.getBlockState(pos).getBlock().getName().getString();
        return MINABLE_BLOCKS.stream().anyMatch(blockName::contains) && 
               !level.getBlockState(pos).getMaterial().isReplaceable();
    }
    
    private boolean isValidTarget(BlockPos pos) {
        Level level = client.level;
        if (level == null) return false;
        
        return isHardStone(level, pos);
    }
    
    private void rotateTowards(LocalPlayer player, Vec3 target) {
        Vec3 eyePos = player.getEyePosition(1.0f);
        Vec3 direction = target.subtract(eyePos).normalize();
        
        double dx = direction.x;
        double dy = direction.y;
        double dz = direction.z;
        
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        
        float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
        float pitch = (float) Math.toDegrees(-Math.atan2(dy, horizontalDistance));
        
        player.setXRot(Math.max(-90.0f, Math.min(90.0f, pitch)));
        player.setYRot(yaw);
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
        if (!active) {
            this.currentTarget = null;
            this.blockDamage = 0.0f;
            this.miningTicks = 0;
        }
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public BlockPos getCurrentTarget() {
        return currentTarget;
    }
}
