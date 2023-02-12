package me.sakuratao.chapterframework.api;

import me.sakuratao.chapterframework.data.DataAccessor;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.handler.DataAccessorHandler;

import java.util.UUID;
import java.util.function.Consumer;

public interface CFW_API {

    ChapterData chapterData();

    default DataAccessor getAccessor() {
        return DataAccessorHandler.STATIC_INSTANCE.getDataAccessor();
    }

}
