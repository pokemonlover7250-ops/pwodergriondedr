package com.skyblock.powder;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import com.skyblock.powder.config.ModConfig;
import com.skyblock.powder.automation.AutoMiner;
import com.skyblock.powder.automation.Pathfinder;
import com.skyblock.powder.detection.ChestDetector;
import com.skyblock.powder.ui.GuiToggle;

@Environment(EnvType.CLIENT)
public class PowderGrinderMod implements ClientModInitializer {
    
    public static final String MOD_ID = "powder-grinder";
    private static AutoMiner autoMiner;
    private static Pathfinder pathfinder;
    private static ChestDetector chestDetector;
    private static ModConfig config;
    
    @Override
    public void onInitializeClient() {
        config = new ModConfig();
        autoMiner = new AutoMiner();
        pathfinder = new Pathfinder();
        chestDetector = new ChestDetector();
        
        // Register client tick event for automation loop
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (config.isEnabled()) {
                tick();
            }
        });
        
        // Register GUI toggle handler
        GuiToggle.register();
    }
    
    private static void tick() {
        if (autoMiner.isActive()) {
            autoMiner.update();
            pathfinder.update();
            chestDetector.update();
        }
    }
    
    public static AutoMiner getAutoMiner() {
        return autoMiner;
    }
    
    public static Pathfinder getPathfinder() {
        return pathfinder;
    }
    
    public static ChestDetector getChestDetector() {
        return chestDetector;
    }
    
    public static ModConfig getConfig() {
        return config;
    }
}
