package me.sakuratao.chapterframework.data;

import me.sakuratao.chapterframework.data.player.PlayerData;

import java.util.function.Consumer;

public interface DataAccessor {
    void readPlayerDataByPlayerNameAsync(String playerName, Consumer<PlayerData> callback);
    void savePlayerDataAsync(PlayerData playerData);
}
