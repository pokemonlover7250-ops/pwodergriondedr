package com.skyblock.powder.ui;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import com.skyblock.powder.PowderGrinderMod;

public class GuiToggle {
    
    private static KeyMapping toggleModKey;
    private static KeyMapping toggleMiningKey;
    private static KeyMapping togglePathfindingKey;
    private static KeyMapping toggleChestKey;
    private static KeyMapping toggleParticlesKey;
    
    private static long lastToggleTime = 0;
    private static final long TOGGLE_COOLDOWN = 200; // 200ms cooldown
    
    public static void register() {
        // Register key bindings
        toggleModKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.powder_grinder.toggle_mod", GLFW.GLFW_KEY_P, "category.powder_grinder")
        );
        
        toggleMiningKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.powder_grinder.toggle_mining", GLFW.GLFW_KEY_M, "category.powder_grinder")
        );
        
        togglePathfindingKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.powder_grinder.toggle_pathfinding", GLFW.GLFW_KEY_N, "category.powder_grinder")
        );
        
        toggleChestKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.powder_grinder.toggle_chest", GLFW.GLFW_KEY_C, "category.powder_grinder")
        );
        
        toggleParticlesKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.powder_grinder.toggle_particles", GLFW.GLFW_KEY_V, "category.powder_grinder")
        );
        
        // Register tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            handleKeyPresses();
        });
    }
    
    private static void handleKeyPresses() {
        long currentTime = System.currentTimeMillis();
        
        if (currentTime - lastToggleTime < TOGGLE_COOLDOWN) {
            return;
        }
        
        Minecraft client = Minecraft.getInstance();
        
        // Don't allow keybinds in pause screen
        if (client.screen instanceof PauseScreen) {
            return;
        }
        
        if (toggleModKey.consumeClick()) {
            lastToggleTime = currentTime;
            PowderGrinderMod.getConfig().toggleEnabled();
            boolean enabled = PowderGrinderMod.getConfig().isEnabled();
            sendMessage(enabled ? "§aGrinder Enabled" : "§cGrinder Disabled");
        }
        
        if (toggleMiningKey.consumeClick()) {
            lastToggleTime = currentTime;
            PowderGrinderMod.getConfig().toggleAutoMining();
            boolean enabled = PowderGrinderMod.getConfig().isAutoMiningEnabled();
            sendMessage(enabled ? "§aAuto Mining Enabled" : "§cAuto Mining Disabled");
        }
        
        if (togglePathfindingKey.consumeClick()) {
            lastToggleTime = currentTime;
            PowderGrinderMod.getConfig().togglePathfinding();
            boolean enabled = PowderGrinderMod.getConfig().isPathfindingEnabled();
            sendMessage(enabled ? "§aPathfinding Enabled" : "§cPathfinding Disabled");
        }
        
        if (toggleChestKey.consumeClick()) {
            lastToggleTime = currentTime;
            PowderGrinderMod.getConfig().toggleChestDetection();
            boolean enabled = PowderGrinderMod.getConfig().isChestDetectionEnabled();
            sendMessage(enabled ? "§aChest Detection Enabled" : "§cChest Detection Disabled");
        }
        
        if (toggleParticlesKey.consumeClick()) {
            lastToggleTime = currentTime;
            PowderGrinderMod.getConfig().toggleParticles();
            boolean enabled = PowderGrinderMod.getConfig().isParticlesEnabled();
            sendMessage(enabled ? "§aParticles Enabled" : "§cParticles Disabled");
        }
    }
    
    private static void sendMessage(String message) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null) {
            client.player.displayClientMessage(Component.literal(message), true);
        }
    }
}
