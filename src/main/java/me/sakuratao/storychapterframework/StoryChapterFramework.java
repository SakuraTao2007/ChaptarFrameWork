package me.sakuratao.storychapterframework;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import me.sakuratao.storychapterframework.api.SCFWAPI;
import me.sakuratao.storychapterframework.api.SCFWAPIProvider;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
public final class StoryChapterFramework extends JavaPlugin {

    @Getter
    public static StoryChapterFramework staticInstance;

    @Getter
    public ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

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
