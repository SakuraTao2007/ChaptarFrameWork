package me.sakuratao.chapterframework.tasks;

import me.sakuratao.chapterframework.handler.DataAccessorHandler;
import me.sakuratao.chapterframework.handler.PlayerDataHandler;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DataSaveLoopTask implements Runnable, MessageHelper {

    PlayerDataHandler playerDataHandler = PlayerDataHandler.STATIC_INSTANCE;

    @Override
    public void run() {

        AtomicInteger j = new AtomicInteger();

        playerDataHandler.mapObject.values().forEach( o -> {
            j.getAndIncrement();
            DataAccessorHandler.STATIC_INSTANCE.getDataAccessor().savePlayerDataAsync(o);
        } );
        printDebug("已完成一次全局数据存储 | " + j.getAndIncrement() + "条数据", false);

    }

}
