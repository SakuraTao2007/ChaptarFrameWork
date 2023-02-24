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

    @Configuration(value = "AUTO_SAVE_DATA")
    public static boolean AUTO_SAVE_DATA = true;

    @Configuration(value = "AUTO_SAVE_TIME")
    public static int AUTO_SAVE_TIME = 30;

    @Configuration(value = "GUI.STARTGUI.TITLE")
    public static String STARTGUI_TITLE = "&a开始行动";
    @Configuration(value = "GUI.STARTGUI.ROWS")
    public static int STARTGUI_ROWS = 3;
    @Configuration(value = "GUI.STARTGUI.ITEMS")
    public static List<String> STARTGUI_ITEMS = Arrays.asList(
            "1:STONE:1:这是一个示例<l>这是一个示例<l>:CONFIRM",
            "ROWS:MATERIAL:AMOUNT:Lore<l>Lore<l>:"
            );

}
