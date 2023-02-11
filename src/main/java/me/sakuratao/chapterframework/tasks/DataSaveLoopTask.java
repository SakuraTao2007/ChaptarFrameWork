package me.sakuratao.chapterframework.tasks;

import me.sakuratao.chapterframework.handler.DataAccessorHandler;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;

public class DataSaveLoopTask implements Runnable{

    PlayerDataHandler playerDataHandler = PlayerDataHandler.STATIC_INSTANCE;

    @Override
    public void run() {

        playerDataHandler.mapObject.values().forEach( o -> DataAccessorHandler.STATIC_INSTANCE.getDataAccessor().savePlayerDataAsync(o) );

    }

}
