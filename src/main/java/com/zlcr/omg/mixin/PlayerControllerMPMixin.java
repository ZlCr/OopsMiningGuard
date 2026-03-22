package com.zlcr.omg.mixin;

import net.minecraft.client.multiplayer.PlayerControllerMP;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public abstract class PlayerControllerMPMixin {

    @Shadow
    private int blockHitDelay;

    /*
     * 在破坏方块成功返回后，修改延迟值
     */
    @Inject(method = "onPlayerDestroyBlock", at = @At("RETURN"))
    private void onDestroyBlock(int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            this.blockHitDelay = 5; // 0 表示无延迟（类似创造模式）或者设置其他值：5 是原版创造模式默认值
        }
    }
}
