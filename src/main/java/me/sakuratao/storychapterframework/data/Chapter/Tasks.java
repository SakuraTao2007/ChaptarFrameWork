package me.sakuratao.storychapterframework.data.Chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Tasks {

    int id = 1; // 任务顺序名
    String taskName = null; // 任务名
    List<String> setting = new ArrayList<>(); // 任务执行

}
