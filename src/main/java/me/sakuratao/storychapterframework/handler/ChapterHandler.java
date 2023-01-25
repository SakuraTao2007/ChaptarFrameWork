package me.sakuratao.storychapterframework.handler;

import me.sakuratao.storychapterframework.StoryChapterFrameWork;
import me.sakuratao.storychapterframework.data.Chapter.ChapterData;
import me.sakuratao.storychapterframework.data.Chapter.Tasks;
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
    StoryChapterFrameWork storyChapterFrameWork;

    private final String path = storyChapterFrameWork.getDataFolder().getPath() + "chapter";

    private final List<ChapterData> chapterList = new ArrayList<>();

    public void init(){

        File pathFile = new File(path);
        if (!pathFile.exists()){
            if (pathFile.mkdirs()) {
                storyChapterFrameWork.getLogger().info("[!] WARM! WARM! WARM! WARM! WARM! WARM!");
                storyChapterFrameWork.getLogger().info("[!]          默认剧情文件夹不存在         ");
                storyChapterFrameWork.getLogger().info("[!]              已重新生成              ");
                storyChapterFrameWork.getLogger().info("[!] WARM! WARM! WARM! WARM! WARM! WARM!");
            }
        } else {
            for (File file : Objects.requireNonNull(pathFile.listFiles())) {

                YamlConfiguration chapter = YamlConfiguration.loadConfiguration(file);

                ChapterData chapterData = new ChapterData();
                chapterData.setId(chapter.getInt("chapter_id"));
                chapterData.setChapter_name(chapter.getString("chapter_name"));

                storyChapterFrameWork.getLogger().info("| 正在加载章节 " + chapterData.getChapter_name() + " | ID: " + chapterData.getId() + "...");

                for (String section : Objects.requireNonNull(chapter.getConfigurationSection("section")).getKeys(false)){

                    String sectionName = chapter.getString("section." + section);
                    List<Tasks> tasksList = new ArrayList<>();
                    for (String tasks : chapter.getConfigurationSection("section." + sectionName + ".tasks").getKeys(false)) {

                        Tasks task = new Tasks();
                        task.setId(chapter.getInt("section." + sectionName + ".tasks." + tasks + ".id"));
                        task.setTaskName(chapter.getString("section." + sectionName + ".tasks." + tasks + ".name"));
                        task.setSetting(chapter.getStringList("section." + sectionName + ".tasks." + tasks + "setting"));
                        tasksList.add(task);

                    }
                    chapterData.getSection().put(sectionName, tasksList);
                    storyChapterFrameWork.getLogger().info("| 已加载子节 " + sectionName + ".");

                }

                chapterList.add(chapterData);
                storyChapterFrameWork.getLogger().info("| 已加载章节 " + chapterData.getChapter_name() + " | ID: " + chapterData.getId() + ".");

            }
        }
        
    }

    public void execute(Player player, ChapterData chapterData){



    }


}
