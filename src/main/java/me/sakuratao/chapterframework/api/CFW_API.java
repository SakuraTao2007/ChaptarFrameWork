package me.sakuratao.chapterframework.api;

import me.sakuratao.chapterframework.data.DataAccessor;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.player.PlayerData;

import java.util.UUID;
import java.util.function.Consumer;

public interface CFW_API {

    DataAccessor dataAccessor = new DataAccessor() {
        @Override
        public void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback) {
            // TODO: 2023/1/20

        }

        @Override
        public void savePlayerDataAsync(PlayerData playerData) {
            // TODO: 2023/1/20
        }

        @Override
        public void readStoryDataByIDAsync(int id, Consumer<ChapterData> callback) {
            // TODO: 2023/1/20
        }

        @Override
        public void saveStoryDataAsync(ChapterData storyData) {
            // TODO: 2023/1/20
        }
    };

    default DataAccessor getAccessor() {
        return dataAccessor;
    }

}
