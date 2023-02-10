package me.sakuratao.chapterframework.listener;

import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.data.cache.CacheData;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.enums.PermissionType;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import me.sakuratao.chapterframework.tasks.RegularLoopExecutionTask;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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

    public PlayerListener() {
    }

    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage("");

        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player, player.getName(), player.getUniqueId());

        if (chapterFramework.isDebugged()) {
            if (playerDataHandler.putPlayerData(player.getUniqueId(), playerData)) {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (p.hasPermission(PermissionType.COMMAND.getPermission()))
                        p.sendMessage(translateColor("&8&| &7&o&nDebug / 已创建并存储 PlayerData for " + player.getName()));
                });
            } else {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (p.hasPermission(PermissionType.COMMAND.getPermission()))
                        p.sendMessage(translateColor("&8&| &7&o&nDebug / &c&o&n无法存储 PlayerData for " + player.getName()));
                });
            }
        }

        chapterFramework.getCacheData().getBukkitTaskMap().put(player, taskTimerAsync(player, new RegularLoopExecutionTask(player),  0, 3));

    }

    public void onPlayerQuit(PlayerQuitEvent event){

        event.setQuitMessage("");

        Player player = event.getPlayer();
        chapterFramework.getCacheData().getBukkitTaskMap().get(event.getPlayer()).cancel();
        chapterFramework.getCacheData().getBukkitTaskMap().remove(player);

    }


}
