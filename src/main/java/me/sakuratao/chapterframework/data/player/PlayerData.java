package me.sakuratao.chapterframework.data.player;

import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.Chapter.SectionData;
import me.sakuratao.chapterframework.data.Chapter.TaskData;
import me.sakuratao.chapterframework.enums.DelayType;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
@DatabaseTable(tableName = "chapter_player_data")
public class PlayerData{

    Player player;
    String playerName;
    UUID playerUuid;

    Long delayTime;
    DelayType delayType;

    ProgressData progressData;

    public PlayerData(Player player, String playerName, UUID playerUuid){
        this.player = player;
        this.playerName = playerName;
        this.playerUuid = playerUuid;
    }

}
class ProgressData{


    ChapterData chapterData;
    SectionData sectionData;
    TaskData taskData;

}

