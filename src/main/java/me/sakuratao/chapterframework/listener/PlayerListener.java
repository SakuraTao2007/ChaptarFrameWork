package me.sakuratao.chapterframework.listener;

import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.data.cache.CacheData;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.data.player.ProgressData;
import me.sakuratao.chapterframework.enums.ConditionType;
import me.sakuratao.chapterframework.enums.PermissionType;
import me.sakuratao.chapterframework.handler.ChapterHandler;
import me.sakuratao.chapterframework.handler.DataAccessorHandler;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import me.sakuratao.chapterframework.tasks.RegularLoopExecutionTask;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import top.jingwenmc.spigotpie.common.event.SpigotEventListener;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

@PieComponent
@SpigotEventListener
public class PlayerListener implements Listener, MessageHelper, SchedulerHelper {

    @Wire
    ChapterFramework chapterFramework;
    @Wire
    PlayerDataHandler playerDataHandler;
    @Wire
    DataAccessorHandler dataAccessorHandler;

    public PlayerListener() {
    }

    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage("");

        Player player = event.getPlayer();
        dataAccessorHandler.getDataAccessor().readPlayerDataByPlayerNameAsync(player.getName(), playerData -> {
            if(playerData == null) { // FIXME: 2023/2/24 playerdata 返回null
                PlayerData playerData1 = new PlayerData(player, player.getName(), player.getUniqueId());
                playerData1.setProgressEncode("0");
                playerDataHandler.putPlayerData(playerData1);
                return;
            } else {
                playerData.setProgressData(new ProgressData());
                playerData.getProgressData().setChapterData(ChapterHandler.STATIC_INSTANCE.getChapterData());
                if (playerDataHandler.putPlayerData(player.getUniqueId(), playerData)) {
                    printDebug("已创建并存储 PlayerData for " + player.getName(), false);
                } else {
                    printDebug("无法存储 PlayerData for " + player.getName(), true);
                }
            }
            chapterFramework.getCacheData().getBukkitTaskMap().put(player, taskTimerAsync(player, new RegularLoopExecutionTask(player, playerData),  0, 3));
        });

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

        event.setQuitMessage("");

        Player player = event.getPlayer();
        if (chapterFramework.getCacheData().getBukkitTaskMap().containsKey(event.getPlayer())) {
            chapterFramework.getCacheData().getBukkitTaskMap().get(event.getPlayer()).cancel();
            chapterFramework.getCacheData().getBukkitTaskMap().remove(player);
        }
        dataAccessorHandler.getDataAccessor().savePlayerDataAsync(playerDataHandler.getPlayerData(player));
        playerDataHandler.removePlayerData(player);

    }

    @EventHandler
    public void onConditionWithMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = playerDataHandler.getPlayerData(player); // TODO: 2023/2/24  
        if (playerData.getConditionData() == null || playerData.getConditionData().getType() != ConditionType.MOVE) return;
        printDebug("", false);
    }

    @EventHandler
    public void onConditionWithInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = playerDataHandler.getPlayerData(player); // TODO: 2023/2/24  
        if (playerData.getConditionData() == null || playerData.getConditionData().getType() != ConditionType.INTERACT) return;
        printDebug("", false);
    }

}
