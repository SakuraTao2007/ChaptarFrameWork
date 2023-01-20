package me.sakuratao.storychapterframework.handler;

import me.sakuratao.storychapterframework.handler.sub.*;

public class HandlerManager {

    private final ConfigHandler configHandler = new ConfigHandler();
    private final MessageHandler messageHandler = new MessageHandler();
    private final CommandHandler commandHandler = new CommandHandler();
    private final PlayerDataHandler playerDataHandler = new PlayerDataHandler();
    private final StoryChapterHandler storyChapterHandler = new StoryChapterHandler();

    public HandlerManager() {
    }

}
