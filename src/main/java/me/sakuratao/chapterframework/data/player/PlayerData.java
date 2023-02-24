package me.sakuratao.chapterframework.data.player;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.enums.DelayType;
import me.sakuratao.chapterframework.enums.PermissionType;
import me.sakuratao.chapterframework.handler.ChapterHandler;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "chapter_player_data")
public class PlayerData implements MessageHelper {

    Player player;

    @DatabaseField(id = true, columnName = "playerName")
    String playerName;
    @DatabaseField(columnName = "playerUuid")
    UUID playerUuid;

    Long delayTime; // 用于延迟执行
    DelayType delayType; // 用于延迟执行

    ConditionData conditionData = null;
    ProgressData progressData;

    @DatabaseField(columnName = "progressEncode")
    String progressEncode = "";

    public PlayerData(Player player, String playerName, UUID playerUuid){
        this.player = player;
        this.playerName = playerName;
        this.playerUuid = playerUuid;
        progressData = new ProgressData();
        progressData.chapterData = ChapterHandler.STATIC_INSTANCE.getChapterData();
    }

    public void decodeProgress(@NotNull String progress) {
        if (progress.equalsIgnoreCase("0")) {
            progressData.setSectionData(progressData.chapterData.getSections().get(0));
            progressData.setTaskData(progressData.sectionData.getTasks().get(0));
            printDebug("初始化玩家 " + player.getName() + "剧情数据", false);
            printDebug("章节: " + progressData.getChapterData().getChapterName(), false);
            printDebug("子节: " + progressData.getSectionData().getId(), false);
            printDebug("任务: " + progressData.getTaskData().getId(), false);
            return;
        }
        String[] decode = progress.split(":");
        if (decode.length != 3) {
            printDebug(player.getName() + " 的 PlayerData progress 数据存储出现错误!", true);
            printDebug("相关信息: 需要 decode 的 progress 长度小于 3", true);
        }
        if (!decode[0].equalsIgnoreCase(progressData.chapterData.getChapterName())){ // 用于判断章节是否发生变化
            progressData.setSectionData(progressData.chapterData.getSections().get(0));
            progressData.setTaskData(progressData.sectionData.getTasks().get(0));
            return;
        }
        progressData.setSectionData(progressData.chapterData.getSections().get(Integer.parseInt(decode[1])));
        progressData.setTaskData(progressData.sectionData.getTasks().get(Integer.parseInt(decode[2])));
    }

    public void encodeProgress() {
        progressEncode = progressData.toString();
    }

}

