package me.sakuratao.storychapterframework.handler;

import me.sakuratao.storychapterframework.data.Chapter.ChapterData;
import me.sakuratao.storychapterframework.data.DataAccessor;
import me.sakuratao.storychapterframework.data.player.PlayerData;
import me.sakuratao.storychapterframework.data.storage.DatabaseAccessor;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Consumer;

@PieComponent
public class DataAccessorHandler implements DataAccessor {

    @Wire
    DatabaseAccessor databaseAccessor;

    public DataAccessorHandler() throws SQLException {
        databaseAccessor.reconnect();
    }

    @Override
    public void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback) {

    }

    @Override
    public void savePlayerDataAsync(PlayerData playerData) {

    }

    @Override
    public void readStoryDataByIDAsync(int id, Consumer<ChapterData> callback) {

    }

    @Override
    public void saveStoryDataAsync(ChapterData storyData) {

    }
}
