package me.sakuratao.storychapterframework.data.storage;

import me.sakuratao.storychapterframework.data.DataAccessor;
import me.sakuratao.storychapterframework.data.Story.StoryData;
import me.sakuratao.storychapterframework.data.player.PlayerData;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

import java.util.UUID;
import java.util.function.Consumer;

@PieComponent
public class DataAccessorImpl implements DataAccessor {
    @Override
    public void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback) {

    }

    @Override
    public void savePlayerDataAsync(PlayerData playerData) {

    }

    @Override
    public void readStoryDataByIDAsync(int id, Consumer<StoryData> callback) {

    }

    @Override
    public void saveStoryDataAsync(StoryData storyData) {

    }
}
