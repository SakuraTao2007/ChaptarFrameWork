package me.sakuratao.storychapterframework.handler.sub;

import me.sakuratao.storychapterframework.utils.helper.SchedulerHelper;
import top.jingwenmc.spigotpie.common.command.CommandSender;
import top.jingwenmc.spigotpie.common.command.PieCommand;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
public class CommandHandler implements SchedulerHelper {

    @PieCommand(value = "scfw", bungeeCord = false, spigot = true)
    public void onMainCommand(CommandSender sender, String args1, String args2){

    }

}
