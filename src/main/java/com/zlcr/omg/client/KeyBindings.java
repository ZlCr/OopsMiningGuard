package com.zlcr.omg.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import com.zlcr.omg.Config;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding toggleKey;

    public static void register() {
        toggleKey = new KeyBinding("key.omg.toggle", Keyboard.KEY_NONE, "key.categories.gameplay");
        ClientRegistry.registerKeyBinding(toggleKey);
        cpw.mods.fml.common.FMLCommonHandler.instance()
            .bus()
            .register(new KeyBindings());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (toggleKey.isPressed()) {
            // 切换启用状态
            Config.enabled = !Config.enabled;

            // 获取本地化状态文本
            String stateKey = Config.enabled ? "omg.state.enabled" : "omg.state.disabled";
            String state = StatCollector.translateToLocal(stateKey);

            // 发送本地化消息
            String message = StatCollector.translateToLocalFormatted("omg.message.toggle", state);
            Minecraft mc = Minecraft.getMinecraft();
            if (mc != null && mc.thePlayer != null) {
                mc.thePlayer.addChatMessage(new ChatComponentText(message));
            }
        }
    }
}
