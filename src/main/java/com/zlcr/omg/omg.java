package com.zlcr.omg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zlcr.omg.client.KeyBindings;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Omg.MODID, version = Tags.VERSION, name = "OopsMiningGuard", acceptedMinecraftVersions = "[1.7.10]")
public class Omg {

    public static final String MODID = "omg";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // 读取配置文件
        Config.load(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide()
            .isClient()) {
            // 注册快捷键（FML 事件）
            KeyBindings.register();

            // 注册玩家登录事件（FML 事件）
            // cpw.mods.fml.common.FMLCommonHandler.instance()
            // .bus()
            // .register(new PlayerLoginHandler());
        }
    }
}
