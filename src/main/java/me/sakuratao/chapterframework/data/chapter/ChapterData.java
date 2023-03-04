package me.sakuratao.chapterframework.data.chapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ChapterData {

    String chapterName = "";
    String chapterVersion = "";
    List<SectionData> sections = new ArrayList<>();

}
