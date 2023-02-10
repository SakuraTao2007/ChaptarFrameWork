package me.sakuratao.chapterframework.tasks;

import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import org.bukkit.entity.Player;

public class RegularLoopExecutionTask implements Runnable {

    Player player;

    PlayerData playerData;

    public RegularLoopExecutionTask(Player player) {
        this.player = player;
        this.playerData = PlayerDataHandler.STATIC_INSTANCE.getPlayerData(player);
    }

    @Override
    public void run() {

        if (playerData.getDelayTime() > System.currentTimeMillis()) { return; }


    }

}
