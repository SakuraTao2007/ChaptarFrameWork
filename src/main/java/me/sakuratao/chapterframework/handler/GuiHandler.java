package me.sakuratao.chapterframework.handler;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import top.jingwenmc.spigotpie.common.configuration.Configuration;
import top.jingwenmc.spigotpie.common.configuration.ConfigurationFile;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

import java.util.*;
import java.util.stream.Collectors;

@PieComponent
@ConfigurationFile("gui.yml")
public class GuiHandler {

    @Configuration(value = "GUI.STARTGUI.TITLE")
    public static String STARTGUI_TITLE = "&a开始行动";
    @Configuration(value = "GUI.STARTGUI.ROWS")
    public static int STARTGUI_ROWS = 3;
    @Configuration(value = "GUI.STARTGUI.ITEMS")
    public static List<String> STARTGUI_ITEMS = Arrays.asList(
            "1:STONE:1:石头:这是一个示例<l>这是一个示例<l>:CONFIRM",
            "ROWS:MATERIAL:AMOUNT:NAME:Lore<l>Lore<l>:"
    );

    /**
     *
     * 创建一个 GUI
     *
     * @param title - 标题
     * @param rows - 行数
     * @param guiItemMap - 物品
     * @return - 生成好的 GUI
     */
    public Gui makeGui(String title, int rows, Map<Integer, GuiItem> guiItemMap){

        Gui gui = Gui.gui()
                .title(Component.text(title))
                .rows(rows)
                .create();
        guiItemMap.forEach(gui::setItem);
        return gui;

    }

    /**
     *
     * 创建一个 GUI 并打开
     *
     * @param player - 打开对象
     * @param title - 标题
     * @param rows - 行数
     * @param guiItemMap - 物品
     */
    public void makeGuiAndOpen(Player player, String title, int rows, Map<Integer, GuiItem> guiItemMap){

        Gui gui = Gui.gui()
                .title(Component.text(title))
                .rows(rows)
                .create();
        guiItemMap.forEach(gui::setItem);
        gui.open(player);

    }

}
