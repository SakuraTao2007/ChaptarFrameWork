package me.sakuratao.chapterframework.data.chapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SectionData {

    int id = 1;
    String sectionName = "";
    List<TaskData> tasks = new ArrayList<>();
    
}
