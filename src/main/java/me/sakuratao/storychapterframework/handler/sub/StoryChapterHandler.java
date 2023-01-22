package me.sakuratao.storychapterframework.handler.sub;

import me.sakuratao.storychapterframework.StoryChapterFrameWork;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class StoryChapterHandler {

    private final String path = StoryChapterFrameWork.staticInstance.getDataFolder().getPath() + "chapter";

    public void init(){

        File pathFile = new File(path);
        if (pathFile.exists()){
            File[] files = pathFile.listFiles();
        }

        // TODO: 2023/1/20 决定剧情以什么格式存储 
        
    }


}
