package me.sakuratao.chapterframework.data.Chapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ChapterData {

    int id;
    String chapterName = "";
    String chapterVersion = "";
    List<SectionData> sections = new ArrayList<>();

}
