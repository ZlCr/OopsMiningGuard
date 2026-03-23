package com.zlcr.omg.client;

import com.zlcr.omg.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class PlayerLoginHandler {

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // 只在客户端且是本地玩家时处理
        if (event.player.worldObj.isRemote && event.player == Minecraft.getMinecraft().thePlayer) {
            // 重置为配置中的默认值
            Config.enabled = Config.defaultEnabled;

            // 发送本地化消息
            String stateKey = Config.enabled ? "omg.state.enabled" : "omg.state.disabled";
            String state = StatCollector.translateToLocal(stateKey);
            String message = StatCollector.translateToLocalFormatted("omg.message.login", state);
            event.player.addChatMessage(new ChatComponentText(message));
        }
    }
}