package me.sakuratao.storychapterframework.data.Chapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ChapterData {

    int id;
    String chapterName = null;
    Map<String, List<TaskData>> sections = new HashMap<>();

}
