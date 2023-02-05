package me.sakuratao.chapterframework.data;

import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.player.PlayerData;

import java.util.UUID;
import java.util.function.Consumer;

public interface DataAccessor {
    void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback);
    void savePlayerDataAsync(PlayerData playerData);
    void readStoryDataByIDAsync(int id, Consumer<ChapterData> callback);
    void saveStoryDataAsync(ChapterData storyData);
}
