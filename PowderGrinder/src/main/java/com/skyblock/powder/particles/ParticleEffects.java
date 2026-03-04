package com.skyblock.powder.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import com.skyblock.powder.PowderGrinderMod;
import com.skyblock.powder.config.ModConfig;

import java.util.Set;

public class ParticleEffects {
    
    private final Minecraft client = Minecraft.getInstance();
    private final ModConfig config = PowderGrinderMod.getConfig();
    
    public void renderMiningParticles(BlockPos targetBlock) {
        if (!config.isParticlesEnabled() || targetBlock == null) {
            return;
        }
        
        Level level = client.level;
        if (level == null) {
            return;
        }
        
        Vec3 blockCenter = Vec3.atCenterOf(targetBlock);
        
        // Spawn particles around the block being mined
        int particleCount = (int) (6 * config.getParticleIntensity());
        
        for (int i = 0; i < particleCount; i++) {
            double offsetX = (Math.random() - 0.5) * 1.5;
            double offsetY = (Math.random() - 0.5) * 1.5;
            double offsetZ = (Math.random() - 0.5) * 1.5;
            
            double velocityX = (Math.random() - 0.5) * 0.2;
            double velocityY = Math.random() * 0.2;
            double velocityZ = (Math.random() - 0.5) * 0.2;
            
            client.particleEngine.createParticle(
                ParticleTypes.BLOCK,
                blockCenter.x + offsetX,
                blockCenter.y + offsetY,
                blockCenter.z + offsetZ,
                velocityX,
                velocityY,
                velocityZ
            );
        }
    }
    
    public void renderOreHighlight(Set<BlockPos> oreBlocks) {
        if (!config.isParticlesEnabled() || !config.isHighlightOreParticles()) {
            return;
        }
        
        Level level = client.level;
        if (level == null) {
            return;
        }
        
        for (BlockPos orePos : oreBlocks) {
            Vec3 center = Vec3.atCenterOf(orePos);
            
            // Spawn glowing particles
            int particleCount = (int) (3 * config.getParticleIntensity());
            
            for (int i = 0; i < particleCount; i++) {
                double angle = Math.random() * Math.PI * 2;
                double radius = 0.5;
                
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;
                double offsetY = Math.random() * 1.0;
                
                client.particleEngine.createParticle(
                    ParticleTypes.GLOW,
                    center.x + offsetX,
                    center.y + offsetY,
                    center.z + offsetZ,
                    0,
                    0,
                    0
                );
            }
        }
    }
    
    public void renderChestHighlight(Set<BlockPos> chests) {
        if (!config.isParticlesEnabled()) {
            return;
        }
        
        Level level = client.level;
        if (level == null) {
            return;
        }
        
        for (BlockPos chestPos : chests) {
            Vec3 center = Vec3.atCenterOf(chestPos);
            
            // Spawn emerald particles around chests
            int particleCount = (int) (4 * config.getParticleIntensity());
            
            for (int i = 0; i < particleCount; i++) {
                double angle = Math.random() * Math.PI * 2;
                double radius = 0.7;
                
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;
                double offsetY = 0.5 + Math.sin(System.currentTimeMillis() * 0.001) * 0.3;
                
                client.particleEngine.createParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    center.x + offsetX,
                    center.y + offsetY,
                    center.z + offsetZ,
                    0,
                    0.05,
                    0
                );
            }
        }
    }
    
    public void renderPathParticles(java.util.List<BlockPos> path) {
        if (!config.isParticlesEnabled() || path == null || path.isEmpty()) {
            return;
        }
        
        Level level = client.level;
        if (level == null) {
            return;
        }
        
        for (int i = 0; i < Math.min(path.size(), 32); i++) {
            BlockPos waypoint = path.get(i);
            Vec3 center = Vec3.atCenterOf(waypoint);
            
            client.particleEngine.createParticle(
                ParticleTypes.END_ROD,
                center.x,
                center.y + 0.5,
                center.z,
                0,
                0,
                0
            );
        }
    }
}
