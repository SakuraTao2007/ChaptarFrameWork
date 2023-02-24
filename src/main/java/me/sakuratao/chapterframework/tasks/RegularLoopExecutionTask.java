package me.sakuratao.chapterframework.tasks;

import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.enums.DelayType;
import me.sakuratao.chapterframework.handler.ChapterHandler;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import org.bukkit.entity.Player;

public class RegularLoopExecutionTask implements Runnable, MessageHelper {

    Player player;
    PlayerData playerData;

    int taskSetting = 0;
    public RegularLoopExecutionTask(Player player, PlayerData playerData) {
        this.player = player;
        this.playerData = playerData;
    }

    @Override
    public void run() {

        if (playerData.getDelayTime() > System.currentTimeMillis() || playerData.getConditionData() != null) {
            return;
        }
        if (taskSetting < playerData.getProgressData().getTaskData().getSetting().size()) {
            ChapterHandler.STATIC_INSTANCE.decodeAndExecute(player, playerData.getProgressData().getChapterData(), playerData.getProgressData().getTaskData().getSetting().get(taskSetting));
            printDebug(taskSetting + " | " + player.getName() + " | " + playerData.getProgressData().getTaskData().getSetting().get(taskSetting),false);
            taskSetting++;
        }

    }

}
