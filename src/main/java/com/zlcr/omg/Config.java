package com.zlcr.omg;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int breakDelay = 5;
    public static boolean enabled = true;
    public static boolean defaultEnabled = true; // 记录配置文件中的初始值

    public static void load(File configFile) {
        Configuration config = new Configuration(configFile);
        config.load();

        breakDelay = config.getInt(
            "breakDelay",
            Configuration.CATEGORY_GENERAL,
            5,
            0,
            40,
            "挖掘后冷却时间（单位：tick，20tick=1s），范围 0~40，默认为 5 tick\n"
                + "Mining cooldown in ticks after breaking a block (20 ticks = 1 second)");
        enabled = config.getBoolean(
            "enabled",
            Configuration.CATEGORY_GENERAL,
            true,
            "进入存档后是否默认启用挖掘冷却修改。true为启用，false关闭，在游戏内可通过快捷键临时切换，默认开启\n"
                + "Whether to enable the mining cooldown modification. true = enabled, false = disabled, can be toggled with hotkey in-game");
        defaultEnabled = enabled; // 保存默认值

        if (config.hasChanged()) {
            config.save();
        }
    }
}
