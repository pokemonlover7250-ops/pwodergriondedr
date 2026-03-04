package com.skyblock.powder.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    
    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(float partialTick, long nanoTime, boolean renderLevel, CallbackInfo ci) {
        // Particle rendering can be hooked here if needed
    }
}
