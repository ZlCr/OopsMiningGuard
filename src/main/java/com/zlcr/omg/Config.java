package com.zlcr.omg;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class Config {

    public static int breakDelay = 5;
    public static boolean enabled = true;
    public static boolean defaultEnabled = true;   // 记录配置文件中的初始值

    public static void load(File configFile) {
        Configuration config = new Configuration(configFile);
        config.load();

        breakDelay = config.getInt("breakDelay", Configuration.CATEGORY_GENERAL, 0, 0, 20,
                "Mining cooldown in ticks after breaking a block (20 ticks = 1 second), range 0~40, default 5/n"+
                "挖掘后冷却时间（单位：tick，20tick=1s），范围 0~40，默认为 5 tick"
                );
        enabled = config.getBoolean("enabled", Configuration.CATEGORY_GENERAL, true,
                "Whether to enable the mining cooldown modification. true = enabled, false = disabled. Default true, can be toggled with hotkey in-game/n"+
                "进入存档后是否默认启用挖掘冷却修改。true为启用，false关闭，默认开启，在游戏内可通过快捷键临时切换"
                );
        defaultEnabled = enabled;   // 保存默认值

        if (config.hasChanged()) {
            config.save();
        }
    }
}