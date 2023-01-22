package me.sakuratao.storychapterframework.handler;

import me.sakuratao.storychapterframework.StoryChapterFrameWork;
import me.sakuratao.storychapterframework.utils.ChatColor;
import me.sakuratao.storychapterframework.utils.helper.MessageHelper;
import me.sakuratao.storychapterframework.utils.helper.SchedulerHelper;
import top.jingwenmc.spigotpie.common.command.CommandSender;
import top.jingwenmc.spigotpie.common.command.PieCommand;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

@PieComponent
public class CommandHandler implements SchedulerHelper, MessageHelper {

    @Wire
    StoryChapterFrameWork storyChapterFrameWork;

    @PieCommand(value = "scfw",permission = "scfw.command", bungeeCord = false, spigot = true)
    public void onMainCommand(CommandSender sender){
        sender.sendMessage(ChatColor.translate("&8&m---»--*-------------------------------------*--«---"));
        sender.sendMessage(ChatColor.translate("&r"));
        sender.sendMessage(ChatColor.translate("&r  &f&lStoryChapterFrameWork &8| &fVer: &7" + storyChapterFrameWork.getDescription().getVersion()));
        sender.sendMessage(ChatColor.translate("&r  &fAuthor: &7" + storyChapterFrameWork.getDescription().getAuthors()));
        sender.sendMessage(ChatColor.translate("&r"));
        sender.sendMessage(ChatColor.translate("&r   &7* &f/scfw storyChapter &8| &f查看故事章节列表"));
        sender.sendMessage(ChatColor.translate("&r"));
        sender.sendMessage(ChatColor.translate("&8&m---»--*-------------------------------------*--«---"));
    }

}
