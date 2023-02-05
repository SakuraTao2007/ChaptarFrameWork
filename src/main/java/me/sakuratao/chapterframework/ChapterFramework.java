package me.sakuratao.chapterframework;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import me.sakuratao.chapterframework.api.CFW_API;
import me.sakuratao.chapterframework.api.CFW_APIProvider;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
public final class ChapterFramework extends JavaPlugin {

    @Getter
    public static ChapterFramework STATIC_INSTANCE;

    @Getter
    public ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    @Override
    public void onLoad() {
        STATIC_INSTANCE = this;
    }

    @Override
    public void onEnable() {

        CFW_APIProvider.setCFW_API(new CFW_API() {});

    }

    @Override
    public void onDisable() {
    }

}
