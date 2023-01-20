package me.sakuratao.storychapterframework;

import me.sakuratao.storychapterframework.api.SCFWAPI;
import me.sakuratao.storychapterframework.api.SCFWAPIProvider;
import me.sakuratao.storychapterframework.data.DataAccessor;
import me.sakuratao.storychapterframework.data.Story.StoryData;
import me.sakuratao.storychapterframework.data.player.PlayerData;
import me.sakuratao.storychapterframework.handler.HandlerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.function.Consumer;

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

        SCFWAPIProvider.setSCFWAPI(new SCFWAPI() {
        });

    }

    @Override
    public void onDisable() {

    }

}
