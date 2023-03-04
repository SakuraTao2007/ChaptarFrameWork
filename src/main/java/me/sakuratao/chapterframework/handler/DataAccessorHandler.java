package me.sakuratao.chapterframework.handler;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.data.DataAccessor;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.data.storage.DatabaseAccessor;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.sql.SQLException;
import java.util.function.Consumer;

@PieComponent
public class DataAccessorHandler implements MessageHelper {

    @Wire
    DatabaseAccessor databaseAccessor;
    @Wire
    PlayerDataHandler playerDataHandler;

    public static DataAccessorHandler STATIC_INSTANCE;

    @Getter
    DataAccessor dataAccessor;

    @SneakyThrows
    public void init() {
        STATIC_INSTANCE = this;
        databaseAccessor.reconnect();

        dataAccessor = new DataAccessor() {
            @Override
            public void readPlayerDataByPlayerNameAsync(String playerName, Consumer<PlayerData> callback) {
                ChapterFramework.STATIC_INSTANCE.taskAsync(() -> {
                    try {
                        callback.accept(databaseAccessor.getPlayerDataDao().queryForId(playerName));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void savePlayerDataAsync(PlayerData playerData) {
                ChapterFramework.STATIC_INSTANCE.taskAsync(() -> {
                    try {
                        playerData.encodeProgress();
                        if (databaseAccessor.getPlayerDataDao().queryForId(playerData.getPlayerName()) == null){
                            databaseAccessor.getPlayerDataDao().create(playerData);
                        } else databaseAccessor.getPlayerDataDao().update(playerData);
                        printDebug("已存储 PlayerData for " + playerData.getPlayerName() + "", false);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

        };

    }

}
