package me.sakuratao.storychapterframework;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import me.sakuratao.storychapterframework.api.SCFWAPI;
import me.sakuratao.storychapterframework.api.SCFWAPIProvider;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

@PieComponent
public final class StoryChapterFrameWork extends JavaPlugin {

    @Getter
    public static StoryChapterFrameWork staticInstance;

    @Getter
    public ProtocolManager plibapi = ProtocolLibrary.getProtocolManager();

    @Override
    public void onLoad() {
        staticInstance = this;
    }

    @Override
    public void onEnable() {

        SCFWAPIProvider.setSCFWAPI(new SCFWAPI() {});

    }

    @Override
    public void onDisable() {

    }

}
