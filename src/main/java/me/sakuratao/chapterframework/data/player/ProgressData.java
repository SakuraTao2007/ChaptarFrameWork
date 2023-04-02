package me.sakuratao.chapterframework.data.player;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.sakuratao.chapterframework.data.chapter.ChapterData;
import me.sakuratao.chapterframework.data.chapter.SectionData;
import me.sakuratao.chapterframework.data.chapter.TaskData;

@Data
@NoArgsConstructor
public class ProgressData {

    ChapterData chapterData;
    SectionData sectionData;
    TaskData taskData;

    @Override
    public String toString(){
        return chapterData.getId() + ":" + sectionData.getId() + ":" + taskData.getId();
    }

}
