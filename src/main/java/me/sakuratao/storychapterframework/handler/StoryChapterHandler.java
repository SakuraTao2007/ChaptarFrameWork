package me.sakuratao.storychapterframework.handler;

import me.sakuratao.storychapterframework.StoryChapterFrameWork;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@PieComponent
public class StoryChapterHandler {

    @Wire
    StoryChapterFrameWork storyChapterFrameWork;

    private final String path = storyChapterFrameWork.getDataFolder().getPath() + "chapter";

    public void init(){

        File pathFile = new File(path);
        if (pathFile.exists()){
            File[] files = pathFile.listFiles();
        }

        // TODO: 2023/1/20 决定剧情以什么格式存储 
        
    }


}
