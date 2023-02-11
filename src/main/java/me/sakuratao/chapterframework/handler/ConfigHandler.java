package me.sakuratao.chapterframework.handler;

import top.jingwenmc.spigotpie.common.configuration.BaseConfiguration;
import top.jingwenmc.spigotpie.common.configuration.Configuration;
import top.jingwenmc.spigotpie.common.configuration.ConfigurationFile;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
@ConfigurationFile("config.yml")
public class ConfigHandler extends BaseConfiguration {

    @Configuration(value = "AUTO_SAVE_DATA")
    public static boolean AUTO_SAVE_DATA = true;

    @Configuration(value = "AUTO_SAVE_TIME")
    public static int AUTO_SAVE_TIME = 30;

}
