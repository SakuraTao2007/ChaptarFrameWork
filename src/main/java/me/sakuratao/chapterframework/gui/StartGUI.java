package me.sakuratao.chapterframework.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.handler.ConfigHandler;
import me.sakuratao.chapterframework.handler.GuiHandler;
import me.sakuratao.chapterframework.tasks.RegularLoopExecutionTask;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@PieComponent
public class StartGUI implements SchedulerHelper { // 暂定想法用于开始剧情，指进入当前剧情

    @Wire
    ChapterFramework chapterFramework;
    @Wire
    GuiHandler guiHandler;

    public void open(Player player){

        guiHandler.makeGuiAndOpen(
                player,
                ConfigHandler.STARTGUI_TITLE,
                ConfigHandler.STARTGUI_ROWS,
                ConfigHandler.STARTGUI_ITEMS.stream().collect(Collectors.toMap((k -> Integer.parseInt(k.split(":")[0])), (v -> {
                    String[] split = v.split(":");
                    return ItemBuilder.from(Objects.requireNonNull(Material.getMaterial(split[1]))).amount(Integer.parseInt(split[2])).asGuiItem(); // TODO: 2023/2/12 添加 lore 等 
                }))));

    }


    public void start(Player player){
        chapterFramework.getCacheData().getBukkitTaskMap().put(player, taskTimerAsync(player, new RegularLoopExecutionTask(player),  0, 3));
    }

}
