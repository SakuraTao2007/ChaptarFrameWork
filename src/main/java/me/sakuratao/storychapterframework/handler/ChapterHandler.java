package me.sakuratao.storychapterframework.handler;

import lombok.Getter;
import me.sakuratao.storychapterframework.StoryChapterFramework;
import me.sakuratao.storychapterframework.data.Chapter.ChapterData;
import me.sakuratao.storychapterframework.data.Chapter.SectionData;
import me.sakuratao.storychapterframework.data.Chapter.TaskData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@PieComponent
public class ChapterHandler {

    @Wire
    StoryChapterFramework storyChapterFrameWork;
    @Getter
    private final List<ChapterData> chapterList = new ArrayList<>();

    private final String path = storyChapterFrameWork.getDataFolder().getPath() + "chapter";

    public void init(){

        File pathFile = new File(path);
        if (!pathFile.exists()){
            if (pathFile.mkdirs()) {
                storyChapterFrameWork.getLogger().info("[!] WARM! WARM! WARM! WARM! WARM! WARM!");
                storyChapterFrameWork.getLogger().info("[!]          默认剧情文件夹不存在         ");
                storyChapterFrameWork.getLogger().info("[!]              已重新生成              ");
                storyChapterFrameWork.getLogger().info("[!] !MRAW !MRAW !MRAW !MRAW !MRAW !MRAW");
            }
        } else {
            for (File file : Objects.requireNonNull(pathFile.listFiles())) {

                YamlConfiguration chapter = YamlConfiguration.loadConfiguration(file);


                ChapterData chapterData = new ChapterData();
                chapterData.setId(chapter.getInt("chapter_id"));
                chapterData.setChapterName(chapter.getString("chapter_name"));
                chapterData.setChapterVersion(chapter.getString("version"));

                storyChapterFrameWork.getLogger().info("| 正在加载章节 " + chapterData.getChapterName() + " / ID: " + chapterData.getId() + "...");

                for (String section : Objects.requireNonNull(chapter.getConfigurationSection("section")).getKeys(false)){

                    SectionData sectionData = new SectionData();
                    String sectionName = chapter.getString("section." + section);
                    sectionData.setId(chapter.getInt("section." + sectionName + ".id")); // TODO: 2023/2/5 复核
                    sectionData.setSectionName(sectionName);
                    List<TaskData> tasksList = sectionData.getTasks();
                    for (String tasks : chapter.getConfigurationSection("section." + sectionName + ".tasks").getKeys(false)) {

                        TaskData task = new TaskData();
                        task.setId(chapter.getInt("section." + sectionName + ".tasks." + tasks + ".id"));
                        task.setTaskName(chapter.getString("section." + sectionName + ".tasks." + tasks + ".name"));
                        task.setSetting(chapter.getStringList("section." + sectionName + ".tasks." + tasks + "setting"));
                        tasksList.add(task);

                    }
                    chapterData.getSections().add(sectionData);
                    storyChapterFrameWork.getLogger().info("| 已加载子节 " + sectionName + ".");

                }

                chapterList.add(chapterData);
                storyChapterFrameWork.getLogger().info("| 已加载章节 " + chapterData.getChapterName() + " / ID: " + chapterData.getId() + ".");

            }
        }
        
    }

    public void execute(Player player, ChapterData chapterData){



    }


}
