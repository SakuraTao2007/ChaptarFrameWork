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
    @Wire
    PlayerDataHandler playerDataHandler;

    public static DataAccessorHandler STATIC_INSTANCE;

    @Getter
    final DataAccessor dataAccessor;

    public DataAccessorHandler() throws SQLException {
        STATIC_INSTANCE = this;
        databaseAccessor.reconnect();

        dataAccessor = new DataAccessor() {
            @Override
            public void readPlayerDataByUUIDAsync(UUID uuid, Consumer<PlayerData> callback) {
                // FIXME: 2023/2/11 复查
                try {
                    callback.accept(databaseAccessor.getPlayerDataDao().queryForId(uuid.toString()));
                    callback.andThen(playerDataHandler::putPlayerData);
                    PlayerData playerData = playerDataHandler.getPlayerData(uuid);
                    playerData.decodeProgress(playerData.getProgressEncode());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void savePlayerDataAsync(PlayerData playerData) {

                try {
                    playerData.encodeProgress();
                    databaseAccessor.getPlayerDataDao().createOrUpdate(playerData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        };

    }

}
