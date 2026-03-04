package com.skyblock.powder.config;

public class ModConfig {
    
    private boolean enabled = false;
    private boolean autoMiningEnabled = true;
    private boolean pathfindingEnabled = true;
    private boolean chestDetectionEnabled = true;
    private boolean particlesEnabled = true;
    
    // Mining settings
    private float miningSpeed = 1.0f;
    private int maxMiningDistance = 6;
    private boolean autoRotate = true;
    
    // Pathfinding settings
    private int pathfindingUpdateTicks = 20;
    private float walkSpeed = 1.0f;
    private boolean avoidLava = true;
    
    // Chest detection settings
    private int chestScanDistance = 32;
    private boolean autoOpenChests = false;
    
    // Particle settings
    private float particleIntensity = 1.0f;
    private boolean highlightOreParticles = true;
    
    // Toggle functionality
    public void toggleEnabled() {
        this.enabled = !this.enabled;
    }
    
    public void toggleAutoMining() {
        this.autoMiningEnabled = !this.autoMiningEnabled;
    }
    
    public void togglePathfinding() {
        this.pathfindingEnabled = !this.pathfindingEnabled;
    }
    
    public void toggleChestDetection() {
        this.chestDetectionEnabled = !this.chestDetectionEnabled;
    }
    
    public void toggleParticles() {
        this.particlesEnabled = !this.particlesEnabled;
    }
    
    // Getters
    public boolean isEnabled() {
        return enabled;
    }
    
    public boolean isAutoMiningEnabled() {
        return autoMiningEnabled;
    }
    
    public boolean isPathfindingEnabled() {
        return pathfindingEnabled;
    }
    
    public boolean isChestDetectionEnabled() {
        return chestDetectionEnabled;
    }
    
    public boolean isParticlesEnabled() {
        return particlesEnabled;
    }
    
    public float getMiningSpeed() {
        return miningSpeed;
    }
    
    public int getMaxMiningDistance() {
        return maxMiningDistance;
    }
    
    public boolean isAutoRotate() {
        return autoRotate;
    }
    
    public int getPathfindingUpdateTicks() {
        return pathfindingUpdateTicks;
    }
    
    public float getWalkSpeed() {
        return walkSpeed;
    }
    
    public boolean isAvoidLava() {
        return avoidLava;
    }
    
    public int getChestScanDistance() {
        return chestScanDistance;
    }
    
    public boolean isAutoOpenChests() {
        return autoOpenChests;
    }
    
    public float getParticleIntensity() {
        return particleIntensity;
    }
    
    public boolean isHighlightOreParticles() {
        return highlightOreParticles;
    }
    
    // Setters
    public void setMiningSpeed(float speed) {
        this.miningSpeed = Math.max(0.1f, Math.min(2.0f, speed));
    }
    
    public void setWalkSpeed(float speed) {
        this.walkSpeed = Math.max(0.1f, Math.min(2.0f, speed));
    }
    
    public void setParticleIntensity(float intensity) {
        this.particleIntensity = Math.max(0.0f, Math.min(2.0f, intensity));
    }
}
