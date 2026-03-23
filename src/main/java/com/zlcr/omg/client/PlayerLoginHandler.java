package com.zlcr.omg.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;

import com.zlcr.omg.Config;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerLoginHandler {

    private static boolean hasTriggered = false;

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        // 如果已经触发过，不再重复执行
        if (hasTriggered) {
            return;
        }

        // 只处理本地玩家
        if (!(event.entity instanceof EntityPlayer) || event.entity != Minecraft.getMinecraft().thePlayer) {
            return;
        }

        // 重置配置为默认值（从配置文件读取的初始值）
        Config.enabled = Config.defaultEnabled;

        // 发送本地化消息
        EntityPlayer player = (EntityPlayer) event.entity;
        String stateKey = Config.enabled ? "omg.state.enabled" : "omg.state.disabled";
        String state = StatCollector.translateToLocal(stateKey);
        String message = StatCollector.translateToLocalFormatted("omg.message.login", state);
        player.addChatMessage(new ChatComponentText(message));

        // 标记已触发
        hasTriggered = true;
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        // 只在客户端世界卸载时重置标志
        if (event.world.isRemote) {
            hasTriggered = false;
        }
    }
}
