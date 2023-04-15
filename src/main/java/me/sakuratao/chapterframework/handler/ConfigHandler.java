package me.sakuratao.chapterframework.handler;

import top.jingwenmc.spigotpie.common.configuration.BaseConfiguration;
import top.jingwenmc.spigotpie.common.configuration.Configuration;
import top.jingwenmc.spigotpie.common.configuration.ConfigurationFile;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

import java.util.Arrays;
import java.util.List;

@PieComponent
@ConfigurationFile("config.yml")
public class ConfigHandler extends BaseConfiguration {

    @Configuration(value = "LANGUAGE")
    public static String LANGUAGE = "zh_CN";

    @Configuration(value = "AUTO_SAVE_DATA")
    public static boolean AUTO_SAVE_DATA = true;

    @Configuration(value = "AUTO_SAVE_TIME")
    public static int AUTO_SAVE_TIME = 30;

    @Configuration(value = "LOOP_INTERVAL")
    public static int LOOP_INTERVAL = 5;

}
