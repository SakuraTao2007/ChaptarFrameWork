package me.sakuratao.chapterframework.api;

import me.sakuratao.chapterframework.data.DataAccessor;
import me.sakuratao.chapterframework.data.player.PlayerData;
import me.sakuratao.chapterframework.handler.DataAccessorHandler;
import org.bukkit.entity.Player;

public interface CFW_API {

    PlayerData playerData(Player player);

    default DataAccessor getAccessor() {
        return DataAccessorHandler.STATIC_INSTANCE.getDataAccessor();
    }

}
