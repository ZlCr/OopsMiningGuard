package com.zlcr.omg.mixin;

import net.minecraft.client.multiplayer.PlayerControllerMP;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.zlcr.omg.Config;

@Mixin(PlayerControllerMP.class)
public abstract class PlayerControllerMPMixin {

    @Shadow
    private int blockHitDelay;

    @Inject(method = "onPlayerDestroyBlock", at = @At("RETURN"))
    private void onDestroyBlock(int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && Config.enabled) {
            this.blockHitDelay = Config.breakDelay;
        }
    }
}
