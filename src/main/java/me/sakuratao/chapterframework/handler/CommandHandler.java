package me.sakuratao.chapterframework.handler;

import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.utils.ChatColor;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import top.jingwenmc.spigotpie.common.command.CommandSender;
import top.jingwenmc.spigotpie.common.command.PieCommand;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

@PieComponent
public class CommandHandler implements SchedulerHelper, MessageHelper {

    @Wire
    ChapterFramework storyChapterFrameWork;

    @PieCommand(value = "chapter",permission = "cfw.command", bungeeCord = false, spigot = true)
    public void onMainCommand(CommandSender sender){
        sender.sendMessage(ChatColor.translate("&8&m---»--*-------------------------------------*--«---"));
        sender.sendMessage(ChatColor.translate("&r"));
        sender.sendMessage(ChatColor.translate("&r  &f&lChapterFrameWork &8| &fVer: &7" + storyChapterFrameWork.getDescription().getVersion()));
        sender.sendMessage(ChatColor.translate("&r  &fAuthor: &7" + storyChapterFrameWork.getDescription().getAuthors()));
        sender.sendMessage(ChatColor.translate("&r"));
        sender.sendMessage(ChatColor.translate("&r   &7* &f/chapter &8| &f查看故事章节指令树"));
        sender.sendMessage(ChatColor.translate("&r"));
        sender.sendMessage(ChatColor.translate("&8&m---»--*-------------------------------------*--«---"));
    }

}
