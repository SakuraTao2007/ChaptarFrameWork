package me.sakuratao.chapterframework.data.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sakuratao.chapterframework.enums.ConditionType;

@Data
@NoArgsConstructor
public class ConditionData {

    ConditionType type;

    String condition;

    public ConditionData(ConditionType type, String condition){
        this.type = type;
        this.condition = condition;
    }

}
