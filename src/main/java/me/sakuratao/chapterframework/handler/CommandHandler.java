package me.sakuratao.chapterframework.handler;

import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.utils.CCUtil;
import me.sakuratao.chapterframework.utils.helper.MessageHelper;
import me.sakuratao.chapterframework.utils.helper.SchedulerHelper;
import org.bukkit.Bukkit;
import top.jingwenmc.spigotpie.common.command.CommandSender;
import top.jingwenmc.spigotpie.common.command.PieCommand;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

@PieComponent
public class CommandHandler implements SchedulerHelper, MessageHelper {

    @Wire
    ChapterFramework chapterFrameWork;

    @PieCommand(value = "chapter", permission = "cfw.command", bungeeCord = false, spigot = true)
    public void mainCommand(CommandSender sender){
        sender.sendMessage(CCUtil.translate("&8&m---»--*-------------------------------------*--«---"));
        sender.sendMessage(CCUtil.translate("&r"));
        sender.sendMessage(CCUtil.translate("&r  &f&lChapterFrameWork &8| &fVer: &7" + chapterFrameWork.getDescription().getVersion()));
        sender.sendMessage(CCUtil.translate("&r  &fAuthor: &7" + chapterFrameWork.getDescription().getAuthors()));
        sender.sendMessage(CCUtil.translate("&r"));
        sender.sendMessage(CCUtil.translate("&r   &7* &f/chapter &8| &f查看故事章节指令集"));
        sender.sendMessage(CCUtil.translate("&r"));
        sender.sendMessage(CCUtil.translate("&8&m---»--*-------------------------------------*--«---"));
    }

    @PieCommand(value = "cfw debug", permission = "cfw.debug", bungeeCord = false, spigot = true)
    public void debugCommand(CommandSender sender){
        if (chapterFrameWork.isDebugged()) {
            Bukkit.getOnlinePlayers().forEach( p -> {
                p.sendMessage(translateColor("&8&l| &7&o&nDebug &8~ &7&o&n已关闭 Debug 模式. <关闭者: " + sender.getName() + ">"));
            });
            chapterFrameWork.getLogger().info(translateColor("| Debug ~ 已关闭 Debug 模式. <关闭者: " + sender.getName() + ">"));
            chapterFrameWork.setDebugged(false);
        } else {
            Bukkit.getOnlinePlayers().forEach( p -> {
                p.sendMessage(translateColor("&8&l| &7&o&nDebug &8~ &7&o&n已开启 Debug 模式, Debug 内容将斜体并附加下划线显示 <开启者: " + sender.getName() + ">"));
            });
            chapterFrameWork.getLogger().info(translateColor("| Debug ~ 已开启 Debug 模式, Debug 内容将斜体并附加下划线显示 <开启者: " + sender.getName() + ">"));
            chapterFrameWork.setDebugged(true);
        }
    }

}
