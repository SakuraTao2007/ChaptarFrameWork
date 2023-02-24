package me.sakuratao.chapterframework.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.handler.ConfigHandler;
import me.sakuratao.chapterframework.handler.GuiHandler;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import me.sakuratao.chapterframework.tasks.RegularLoopExecutionTask;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@PieComponent
public class StartGUI implements SchedulerHelper { // 暂定想法用于开始剧情，指进入当前剧情

    @Wire
    ChapterFramework chapterFramework;
    @Wire
    PlayerDataHandler playerDataHandler;
    @Wire
    GuiHandler guiHandler;

    private GuiItem apply(Player p, String v) {
        String[] split = v.split(":");
        List<Component> lore = Arrays.stream(split[3].split("<l>")).map(Component::text).collect(Collectors.toList());
        return ItemBuilder.from(Objects.requireNonNull(Material.getMaterial(split[1]))).amount(Integer.parseInt(split[2])).lore(lore).asGuiItem(event -> {
            event.setCancelled(true);
            if (split[4] != null  && split[4].equals("CONFIRM")){
                start(p);
            }
        }); // TODO: 2023/2/18 附魔添加
    }

    public void open(Player player){

        guiHandler.makeGuiAndOpen(
                player,
                ConfigHandler.STARTGUI_TITLE,
                ConfigHandler.STARTGUI_ROWS,
                ConfigHandler.STARTGUI_ITEMS.stream().collect(Collectors.toMap((k -> Integer.parseInt(k.split(":")[0])), v -> apply(player, v)))
        );

    }


    public void start(Player player){
        chapterFramework.getCacheData().getBukkitTaskMap().put(player, taskTimerAsync(player, new RegularLoopExecutionTask(player, playerDataHandler.getPlayerData(player)),  0, 3));
    }

}
