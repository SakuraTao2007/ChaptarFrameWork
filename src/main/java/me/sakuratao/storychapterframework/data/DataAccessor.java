package me.sakuratao.storychapterframework.data;

import me.sakuratao.storychapterframework.data.Chapter.ChapterData;
import me.sakuratao.storychapterframework.data.player.PlayerData;

import java.util.UUID;
import java.util.function.Consumer;

public interface DataAccessor {
    void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback);
    void savePlayerDataAsync(PlayerData playerData);
    void readStoryDataByIDAsync(int id, Consumer<ChapterData> callback);
    void saveStoryDataAsync(ChapterData storyData);
}
