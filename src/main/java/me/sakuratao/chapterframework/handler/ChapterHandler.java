package me.sakuratao.chapterframework.handler;

import lombok.Getter;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.Chapter.SectionData;
import me.sakuratao.chapterframework.data.Chapter.TaskData;
import me.sakuratao.chapterframework.enums.ActionType;
import me.sakuratao.chapterframework.enums.DelayType;
import me.sakuratao.chapterframework.enums.PermissionType;
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

@PieComponent
public class ChapterHandler implements MessageHelper, SchedulerHelper {

    @Wire
    ChapterFramework chapterFrameWork;
    @Wire
    PlayerDataHandler playerDataHandler;

    @Getter ChapterData chapterData;
    private final String path = chapterFrameWork.getDataFolder().getPath() + "chapter";

    public void init(){

        File pathFile = new File(path);
        if (!pathFile.exists()){
            if (pathFile.mkdirs()) {
                chapterFrameWork.getLogger().info("[!] WARM! WARM! WARM! WARM! WARM! WARM! WARM!");
                chapterFrameWork.getLogger().info("[!]              默认剧情文件夹不存在           ");
                chapterFrameWork.getLogger().info("[!]                  已重新生成                ");
                chapterFrameWork.getLogger().info("[!] !MRAW !MRAW !MRAW !MRAW !MRAW !MRAW !MRAW");
            }
        } else {
            for (File file : Objects.requireNonNull(pathFile.listFiles())) {

                YamlConfiguration chapter = YamlConfiguration.loadConfiguration(file);

                ChapterData chapterData = new ChapterData();
                chapterData.setChapterName(chapter.getString("chapter_name"));
                chapterData.setChapterVersion(chapter.getString("version"));

                chapterFrameWork.getLogger().info("| 正在加载章节 " + chapterData.getChapterName() + " / Ver: " + chapterData.getChapterVersion() + "...");

                for (String section : Objects.requireNonNull(chapter.getConfigurationSection("section")).getKeys(false)){

                    SectionData sectionData = new SectionData();
                    String sectionName = chapter.getString("section." + section);
                    sectionData.setId(chapter.getInt("section." + sectionName + ".id")); // TODO: 2023/2/5 复核
                    sectionData.setSectionName(sectionName);
                    List<TaskData> tasksList = sectionData.getTasks();
                    for (String taskNote : chapter.getConfigurationSection("section." + sectionName + ".tasks").getKeys(false)) {

                        TaskData task = new TaskData();
                        task.setId(chapter.getInt("section." + sectionName + ".tasks." + taskNote + ".id"));
                        task.setTaskName(chapter.getString("section." + sectionName + ".tasks." + taskNote + ".name"));
                        task.setSetting(chapter.getStringList("section." + sectionName + ".tasks." + taskNote + "setting"));
                        tasksList.add(task);

                    }
                    chapterData.getSections().add(sectionData);
                    chapterFrameWork.getLogger().info("| 已加载子节 " + sectionName + ".");

                }

                chapterData.setSections( // 用于重新排序子节与任务，方便后续管理
                    chapterData.getSections().stream()
                        .peek(sectionData -> {
                            sectionData.setTasks(
                                sectionData.getTasks().stream().sorted(Comparator.comparing(TaskData::getId)).toList()
                            );
                        }).sorted(Comparator.comparing(SectionData::getId)).toList()
                );

                this.chapterData = chapterData;
                chapterFrameWork.getLogger().info("| 已加载章节 " + chapterData.getChapterName() + ".");

            }
        }
        
    }

    public void decodeAndExecute(Player player, ChapterData data, String setting) { // throw DAECE (DecodeAndExecuteCodeError)

        taskAsync(() -> {

            List<String> segmentation = Arrays.stream(setting.split("")).toList();
            Audience audience = this.chapterFrameWork.getAdventure().player(player);
            if (segmentation.get(0).equalsIgnoreCase(ActionType.DELAY.getAction())) {
                if (segmentation.size() != 3) {
                    player.sendMessage(translateColor("&8&l| &c出现错误 &8~ &c数据中枢异常, 请上报错误代码至跃空研究所！ -/ CODE: DAECE /-"));
                    throw new RuntimeException("出现错误 | 在章节 " + data.getChapterName() + " 中, 相关信息如下" + setting + " -/ CODE: DAECE /-");
                }
                playerDataHandler.getPlayerData(player).setDelayTime(Long.parseLong(segmentation.get(2)) * 1000 + System.currentTimeMillis());
                if (segmentation.get(2).equalsIgnoreCase("last")) playerDataHandler.getPlayerData(player).setDelayType(DelayType.LAST);
                if (segmentation.get(2).equalsIgnoreCase("next")) playerDataHandler.getPlayerData(player).setDelayType(DelayType.NEXT);
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase(ActionType.TITLE.getAction())) {
                if (segmentation.size() < 5) {
                    player.sendMessage(translateColor("&8&l| &c出现错误 &8~ &c数据中枢异常, 请上报错误代码至跃空研究所！ -/ CODE: DAECE /-"));
                    throw new RuntimeException("出现错误 | 在章节 " + data.getChapterName() + " 中, 相关信息如下" + setting + " -/ CODE: DAECE /-");
                }
                audience.showTitle(
                        Title.title(
                                Component.text(segmentation.get(4)),
                                Component.text(segmentation.size() > 5 ? segmentation.get(50) : ""),
                                Title.Times.times(
                                        Duration.ofSeconds(Long.parseLong(segmentation.get(1))),
                                        Duration.ofSeconds(Long.parseLong(segmentation.get(2))),
                                        Duration.ofSeconds(Long.parseLong(segmentation.get(3))))
                        ));
                return;
            }
            if (segmentation.get(0).equalsIgnoreCase("task")) {
                if (segmentation.size() != 2){
                    player.sendMessage(translateColor("&8&l| &c出现错误 &8~ &c数据中枢异常, 请上报错误代码至跃空研究所！ -/ CODE: DAECE /-"));
                    throw new RuntimeException("出现错误 | 在章节 " + data.getChapterName() + " 中, 相关信息如下" + setting + " -/ CODE: DAECE /-");
                }
                String taskId = segmentation.get(1);
                data.getSections().get(1).getTasks().forEach(t -> {
                    if (!(Integer.getInteger(taskId).equals(t.getId()))){

                    }
                });
            }
             /*
             if (this.chapterFrameWork.isDebugged()) {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (p.hasPermission(PermissionType.COMMAND.getPermission()))
                        p.sendMessage(translateColor("&8&| &7&o&nDebug / " + player.getName()));
                });
            }
            */
        });

    }

}
