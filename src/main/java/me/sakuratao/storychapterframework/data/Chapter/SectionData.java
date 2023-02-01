package me.sakuratao.storychapterframework.data.Chapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class SectionData {

    int id = 1;
    String sectionName = "";
    List<TaskData> tasks = new ArrayList<>();
    
}
