package me.sakuratao.chapterframework;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import lombok.Setter;
import me.sakuratao.chapterframework.api.CFW_API;
import me.sakuratao.chapterframework.api.CFW_APIProvider;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.cache.CacheData;
import me.sakuratao.chapterframework.handler.ChapterHandler;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import javax.annotation.WillClose;

@PieComponent
public final class ChapterFramework extends JavaPlugin {

    public static ChapterFramework STATIC_INSTANCE;

    @Getter ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    @Getter BukkitAudiences adventure;
    @Getter CacheData cacheData = new CacheData();
    @Getter @Setter boolean debugged = false;


    @Wire
    ChapterHandler chapterHandler;

    @Override
    public void onLoad() {STATIC_INSTANCE = this;}

    @Override
    public void onEnable() {
        chapterHandler.init();
        CFW_APIProvider.setCFW_API(new CFW_API() {

            @Override
            public ChapterData chapterData() {
                return chapterHandler.getChapterData();
            }
        });
    }

    @Override
    public void onDisable() {
    }

}
