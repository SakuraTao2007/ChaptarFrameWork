package me.sakuratao.chapterframework;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.sakuratao.chapterframework.api.CFW_API;
import me.sakuratao.chapterframework.api.CFW_APIProvider;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.cache.CacheData;
import me.sakuratao.chapterframework.handler.ChapterHandler;
import me.sakuratao.chapterframework.handler.ConfigHandler;
import me.sakuratao.chapterframework.handler.DataAccessorHandler;
import me.sakuratao.chapterframework.tasks.DataSaveLoopTask;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;
import top.jingwenmc.spigotpie.spigot.SpigotPieSpigot;

import javax.annotation.WillClose;

@PieComponent
public final class ChapterFramework extends JavaPlugin implements SchedulerHelper {

    public static ChapterFramework STATIC_INSTANCE;

    @Getter BukkitAudiences adventure;
    @Getter CacheData cacheData;
    @Getter @Setter boolean debugged = false;


    @Wire
    ChapterHandler chapterHandler;
    @Wire
    DataAccessorHandler dataAccessorHandler;

    @Override
    public void onLoad() {STATIC_INSTANCE = this;}

    @Override
    public void onEnable() {
        getLogger().info("|                                                             ");
        getLogger().info("|                   -/ WELCOME BACK /-                    ");
        getLogger().info("|                                                             ");
        getLogger().info("| _________ .__                   __                          ");
        getLogger().info("| \\_   ___ \\|  |__ _____  _______/  |_  ___________         ");
        getLogger().info("| /    \\  \\/|  |  \\\\__  \\ \\____ \\   __\\/ __ \\_  __ \\");
        getLogger().info("| \\     \\___|   Y  \\/ __ \\|  |_> >  | \\  ___/|  | \\/    ");
        getLogger().info("|  \\______  /___|  (____  /   __/|__|  \\___  >__|           ");
        getLogger().info("|         \\/     \\/     \\/|__|             \\/             ");
        getLogger().info("|                                                             ");
        getLogger().info("|             -/ We are loading SpigotPie /-              ");
        SpigotPieSpigot.inject(this,"META-INF", "org", "com", "dev", "net", "org");
        getLogger().info("|                -/ Loaded SpigotPie /-                   ");
        getLogger().info("|                                                             ");
        getLogger().info("|         -/ We are doing some initialization /-            ");
        cacheData = new CacheData();
        chapterHandler.init();
        dataAccessorHandler.init();
        if (ConfigHandler.AUTO_SAVE_DATA){
            taskTimerAsync(new DataSaveLoopTask(), 0, (long) ConfigHandler.AUTO_SAVE_TIME * 60 * 20);
        }
        CFW_APIProvider.setCFW_API(() -> chapterHandler.getChapterData());
    }

    @Override
    public void onDisable() {
    }

    public ProtocolManager protocolManager() {
        return ProtocolLibrary.getProtocolManager();
    }
}
