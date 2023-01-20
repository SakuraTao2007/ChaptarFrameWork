package me.sakuratao.storychapterframework;

import me.sakuratao.storychapterframework.handler.HandlerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StoryChapterFrameWork extends JavaPlugin {

    public static StoryChapterFrameWork staticInstance;
    public StoryChapterFrameWork instance;

    public HandlerManager handlerManager = new HandlerManager();

    @Override
    public void onLoad() {
        staticInstance = this;
        instance = this;
    }

    @Override
    public void onEnable() {



    }

    @Override
    public void onDisable() {

    }

}
