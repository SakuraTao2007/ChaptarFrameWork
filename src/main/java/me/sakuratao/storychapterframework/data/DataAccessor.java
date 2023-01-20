package me.sakuratao.storychapterframework.data;

import me.sakuratao.storychapterframework.data.Story.StoryData;
import me.sakuratao.storychapterframework.data.player.PlayerData;

import java.util.UUID;
import java.util.function.Consumer;

public interface DataAccessor {
    void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback);
    void savePlayerDataAsync(PlayerData playerData);
    void readStoryDataByIDAsync(int id, Consumer<StoryData> callback);
    void saveStoryDataAsync(StoryData storyData);
}
