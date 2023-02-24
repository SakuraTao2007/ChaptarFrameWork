package me.sakuratao.chapterframework.handler;

import lombok.Getter;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.Chapter.SectionData;
import me.sakuratao.chapterframework.data.Chapter.TaskData;
import me.sakuratao.chapterframework.data.player.ConditionData;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.enums.*;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@PieComponent
public class ChapterHandler implements MessageHelper, SchedulerHelper {

    @Wire
    ChapterFramework chapterFrameWork;
    @Wire
    PlayerDataHandler playerDataHandler;
    @Getter ChapterData chapterData;

    public static ChapterHandler STATIC_INSTANCE;

    public void init(){
        STATIC_INSTANCE = this;
        if (new File(chapterFrameWork.getDataFolder().getPath() + "/debug").exists()) {
            chapterFrameWork.setDebugged(true);
        }
        File pathFile = new File(chapterFrameWork.getDataFolder().getPath() + "/chapter");
        if (!pathFile.exists()){
            if (pathFile.mkdirs()) {
                chapterFrameWork.getLogger().info("|/                                                               ");
                chapterFrameWork.getLogger().info("|\\                        DEFAULT FOLDER NOT FOUND                  ");
                chapterFrameWork.getLogger().info("|/                               WE RESPAWN IT                         ");
                chapterFrameWork.getLogger().info("|\\                                                              ");
            }
        } else {
            for (File file : Objects.requireNonNull(pathFile.listFiles())) {

                YamlConfiguration chapter = YamlConfiguration.loadConfiguration(file);

                ChapterData chapterData = new ChapterData();
                chapterData.setChapterName(chapter.getString("chapter_name"));
                chapterData.setChapterVersion(chapter.getString("version"));

                chapterFrameWork.getLogger().info("| Loading Chapter " + chapterData.getChapterName() + " / Ver: " + chapterData.getChapterVersion() + "...");

                for (String section : Objects.requireNonNull(chapter.getConfigurationSection("section")).getKeys(false)) {

                    SectionData sectionData = new SectionData();
                    String sectionName = chapter.getString("section." + section + ".name");
                    sectionData.setId(chapter.getInt("section." + section + ".id"));
                    sectionData.setSectionName(sectionName);
                    for (String taskNote : Objects.requireNonNull(chapter.getConfigurationSection("section." + section + ".tasks")).getKeys(false)) {

                        TaskData task = new TaskData();
                        task.setId(chapter.getInt("section." + section + ".tasks." + taskNote + ".id"));
                        task.setTaskName(chapter.getString("section." + section + ".tasks." + taskNote + ".name"));
                        task.setSetting(chapter.getStringList("section." + section + ".tasks." + taskNote + ".setting"));
                        sectionData.getTasks().add(task);

                    }
                    chapterData.getSections().add(sectionData);
                    chapterFrameWork.getLogger().info("| Loaded Section " + section + ".");

                }

                chapterData.setSections( // 用于重新排序子节与任务，方便后续管理
                    chapterData.getSections().stream()
                        .peek(sectionData -> sectionData.setTasks(
                            sectionData.getTasks().stream().sorted(Comparator.comparing(TaskData::getId)).toList()
                        )).sorted(Comparator.comparing(SectionData::getId)).toList()
                );

                if (chapterFrameWork.isDebugged()) {
                    for (int i = 0; i < chapterData.getSections().size(); i++) {
                        chapterFrameWork.getLogger().info("C: " + chapterData);
                        chapterFrameWork.getLogger().info("S: " + chapterData.getSections().get(i));
                        for (int j = 0; j < chapterData.getSections().get(i).getTasks().size(); j++) {
                            chapterFrameWork.getLogger().info("T: " + chapterData.getSections().get(i).getTasks().get(j));
                        }
                    }
                }

                this.chapterData = chapterData;
                chapterFrameWork.getLogger().info("| Loaded Chapter " + chapterData.getChapterName() + ".");

            }
        }
        
    }

    public void decodeAndExecute(Player player, ChapterData data, String setting) { // throw DAECE (DecodeAndExecuteCodeError)

        taskAsync(() -> {

            List<String> segmentation = Arrays.stream(setting.split(":")).toList();
            PlayerData playerData = playerDataHandler.getPlayerData(player);
            Audience audience = this.chapterFrameWork.getAdventure().player(player);
            if (segmentation.get(0).equalsIgnoreCase(ActionType.DELAY.getType())) {
                if (segmentation.size() != 4) {
                    player.sendMessage(translateColor("&8&l| &c出现错误 &8~ &c数据中枢异常, 请上报错误代码至跃空研究所！ -/ CODE: DAECE /-")); // FIXME: 2023/2/18 对接 PlayerDataExpand
                    throw new RuntimeException("出现错误 | 在章节 " + data.getChapterName() + " 中, 相关信息如下" + setting + " -/ CODE: DAECE /-");
                }
                TimeUnitType tut = checkTimeUnit(segmentation.get(1));
                switch (tut) {
                    case TICKS ->
                            playerDataHandler.getPlayerData(player).setDelayTime(Long.parseLong(segmentation.get(2)) / 20 * 1000 + System.currentTimeMillis());
                    case SECOND ->
                            playerDataHandler.getPlayerData(player).setDelayTime(Long.parseLong(segmentation.get(2)) * 1000 + System.currentTimeMillis());
                    case MINUTE ->
                            playerDataHandler.getPlayerData(player).setDelayTime(Long.parseLong(segmentation.get(2)) * 60 * 1000 + System.currentTimeMillis());
                    case HOUR ->
                            playerDataHandler.getPlayerData(player).setDelayTime(Long.parseLong(segmentation.get(2)) * 60 * 60 * 1000 + System.currentTimeMillis());
                }
                if (segmentation.get(3).equalsIgnoreCase("last")) playerDataHandler.getPlayerData(player).setDelayType(DelayType.LAST);
                if (segmentation.get(3).equalsIgnoreCase("next")) playerDataHandler.getPlayerData(player).setDelayType(DelayType.NEXT);
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase(ActionType.TITLE.getType())) {
                if (segmentation.size() < 5) {
                    player.sendMessage(translateColor("&8&l| &c出现错误 &8~ &c数据中枢异常, 请上报错误代码至跃空研究所！ -/ CODE: DAECE /-"));
                    throw new RuntimeException("出现错误 | 在章节 " + data.getChapterName() + " 中, 相关信息如下" + setting + " -/ CODE: DAECE /-");
                }
                audience.showTitle(
                        Title.title(
                                Component.text(segmentation.get(4)),
                                Component.text(segmentation.size() > 5 ? segmentation.get(5) : ""),
                                Title.Times.times(
                                        Duration.ofSeconds(Long.parseLong(segmentation.get(1))),
                                        Duration.ofSeconds(Long.parseLong(segmentation.get(2))),
                                        Duration.ofSeconds(Long.parseLong(segmentation.get(3))))
                        ));
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase(ActionType.ACTION_BAR.getType())) {
                audience.sendActionBar(Component.text(segmentation.get(1)));
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase(ActionType.CONDITIONS.getType())) { // TODO: 2023/2/24 同时多重判断
                String condition = segmentation.get(1);
                playerData.setConditionData(new ConditionData(ConditionType.valueOf(condition.substring(0,1).toUpperCase() + condition.substring(1)), segmentation.get(2)));
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase(ActionType.TASK.getType())) {
                if (segmentation.size() != 2){
                    player.sendMessage(translateColor("&8&l| &c出现错误 &8~ &c数据中枢异常, 请上报错误代码至跃空研究所！ -/ CODE: DAECE /-"));
                    throw new RuntimeException("出现错误 | 在章节 " + data.getChapterName() + " 中, 相关信息如下" + setting + " -/ CODE: DAECE /-");
                }
                String taskId = segmentation.get(1);
                playerData.getProgressData().getSectionData().getTasks().forEach(t -> {
                    if (Integer.getInteger(taskId).equals(t.getId()))
                        playerData.getProgressData().setTaskData(t);
                });
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase(ActionType.CHAT_FRAME.getType())) {
                player.sendMessage(segmentation.get(1));
            }
        });

    }

    public TimeUnitType checkTimeUnit(String message) {
        return TimeUnitType.valueOf(message.substring(0, 1).toUpperCase() + message.substring(1).toUpperCase());
    }

}
