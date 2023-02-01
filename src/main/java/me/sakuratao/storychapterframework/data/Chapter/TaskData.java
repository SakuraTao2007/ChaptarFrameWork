package me.sakuratao.storychapterframework.data.Chapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskData {

    int id = 1; // 任务顺序名
    String taskName = null; // 任务名
    List<String> setting = new ArrayList<>(); // 任务执行

}
