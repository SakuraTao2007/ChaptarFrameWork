package me.sakuratao.chapterframework.handler;

import lombok.Getter;
import me.sakuratao.chapterframework.data.Chapter.ChapterData;
import me.sakuratao.chapterframework.data.DataAccessor;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.data.storage.DatabaseAccessor;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Consumer;

@PieComponent
public class DataAccessorHandler {

    @Wire
    DatabaseAccessor databaseAccessor;

    @Getter
    final DataAccessor dataAccessor;

    public DataAccessorHandler() throws SQLException {

        databaseAccessor.reconnect();

        dataAccessor = new DataAccessor() {
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
    }

}
