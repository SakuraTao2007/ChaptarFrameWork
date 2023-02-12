package me.sakuratao.chapterframework.data.player;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.Chapter.SectionData;
import me.sakuratao.chapterframework.data.Chapter.TaskData;

@Data
@NoArgsConstructor
public class ProgressData {

    ChapterData chapterData;
    SectionData sectionData;
    TaskData taskData;

    @Override
    public String toString(){
        return chapterData.getChapterName() + ":" + sectionData.getId() + ":" + taskData.getId();
    }

}
