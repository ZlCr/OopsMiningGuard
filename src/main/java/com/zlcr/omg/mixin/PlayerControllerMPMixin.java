package com.zlcr.omg.mixin;

import net.minecraft.client.multiplayer.PlayerControllerMP;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerControllerMP.class)
public abstract class PlayerControllerMPMixin {

    // @Shadow
    // private int blockHitDelay;

    // /*
    // * 在破坏方块成功返回后，修改延迟值
    // */
    // @Inject(method = "onPlayerDestroyBlock", at = @At("RETURN"))
    // private void onDestroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
    // if (Config.enableMiningCooldown) {
    // // 设置延迟，单位 tick（20 tick = 1秒）
    // this.blockHitDelay = Config.miningCooldownTicks;
    // }
    // }
}
