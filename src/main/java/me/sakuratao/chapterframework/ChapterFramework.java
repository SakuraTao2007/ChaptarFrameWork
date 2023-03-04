package me.sakuratao.chapterframework;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import lombok.Setter;
import me.sakuratao.chapterframework.api.CFW_APIProvider;
import me.sakuratao.chapterframework.data.cache.CacheData;
import me.sakuratao.chapterframework.handler.ChapterHandler;
import me.sakuratao.chapterframework.handler.ConfigHandler;
import me.sakuratao.chapterframework.handler.DataAccessorHandler;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import me.sakuratao.chapterframework.tasks.DataSaveLoopTask;
import me.sakuratao.chapterframework.utils.NMSUtil;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;
import top.jingwenmc.spigotpie.spigot.SpigotPieSpigot;

@PieComponent
public final class ChapterFramework extends JavaPlugin implements SchedulerHelper {

    public static ChapterFramework STATIC_INSTANCE;

    @Getter BukkitAudiences adventure;
    @Getter CacheData cacheData;
    @Getter @Setter boolean debugged = false;

    ProtocolManager protocolManager;

    @Wire
    ChapterHandler chapterHandler;
    @Wire
    PlayerDataHandler playerDataHandler;
    @Wire
    DataAccessorHandler dataAccessorHandler;

    @Override
    public void onLoad() {STATIC_INSTANCE = this;}

    @Override
    public void onEnable() {
        getLogger().info(" | Hi, THERE! WELCOME BACK AGAIN~                              ");
        getLogger().info(" |                                                             ");
        getLogger().info(" | _________ .__                   __                          ");
        getLogger().info(" | \\_   ___ \\|  |__ _____  _______/  |_  ___________         ");
        getLogger().info(" | /    \\  \\/|  |  \\\\__  \\ \\____ \\   __\\/ __ \\_  __ \\");
        getLogger().info(" | \\     \\___|   Y  \\/ __ \\|  |_> >  | \\  ___/|  | \\/    ");
        getLogger().info(" |  \\______  /___|  (____  /   __/|__|  \\___  >__|           ");
        getLogger().info(" |         \\/     \\/     \\/|__|             \\/  Ver: " + getDescription().getVersion());
        getLogger().info(" |                                                             ");
        getLogger().info(" | Checking environment...");
        if (NMSUtil.isSupported()) getLogger().info(" | Bukkit: " + Bukkit.getVersion());
        else {getLogger().info(" | UNSUPPORTED BUKKIT!"); return;}
        if (Integer.parseInt(System.getProperty("java.specification.version")) >= 17) getLogger().info(" | Java: " + System.getProperty("java.specification.version"));
        else {getLogger().info(" | UNSUPPORTED JAVA VERSION!"); return;}
        getLogger().info(" | Passed, the environment is fine!                          ");
        getLogger().info(" |                                                             ");
        getLogger().info(" | Now we are loading SpigotPie...                             ");
        getLogger().info(" |                                                             ");
        SpigotPieSpigot.inject(this,"META-INF", "org", "com", "dev", "net", "org");
        getLogger().info(" |                                                             ");
        getLogger().info(" | Loaded SpigotPie.                                            ");
        getLogger().info(" |                                                             ");
        getLogger().info(" | We are doing some initialization...                            ");
        getLogger().info(" | Data initializing...                                        ");
        adventure = BukkitAudiences.create(this);
        cacheData = new CacheData();
        chapterHandler.init();
        dataAccessorHandler.init();
        getLogger().info(" | Data initialized.                                            ");
        if (ConfigHandler.AUTO_SAVE_DATA){
            taskTimerAsync(new DataSaveLoopTask(), 0, (long) ConfigHandler.AUTO_SAVE_TIME * 60 * 20);
        }
        getLogger().info(" | Checking hooks...                                            ");
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            cacheData.setPAPI(true);
            getLogger().info(" | + Papi Hooked.                                        ");
        }
        if (getServer().getPluginManager().isPluginEnabled("ProtocolLib")) {
            cacheData.setPROTOCOL_LIB(true);
            protocolManager = ProtocolLibrary.getProtocolManager();
            getLogger().info(" | + ProtocolLib Hooked.                                        ");
        }
        if (getServer().getPluginManager().isPluginEnabled("Citizens")) {
            cacheData.setCITIZENS(true);
            protocolManager = ProtocolLibrary.getProtocolManager();
            getLogger().info(" | + Citizens Hooked.                                        ");
        }
        CFW_APIProvider.setCFW_API(player -> playerDataHandler.getPlayerData(player));
        getLogger().info(" | API Setup.                                                   ");
    }

    @Override
    public void onDisable() {
    }

    public ProtocolManager protocolManager() {
        return protocolManager;
    }
}
