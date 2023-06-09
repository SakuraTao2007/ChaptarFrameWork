package me.sakuratao.chapterframework.utils.helper;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.enums.PermissionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public interface MessageHelper {

    /**
     *  用于打印debug信息
     * @param debugMessage - 需要输出的信息
     * @param error - 是否为错误信息
     */
    default void printDebug(String debugMessage, boolean error) {
        if (ChapterFramework.STATIC_INSTANCE.isDebugged()) {
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (!p.hasPermission(PermissionType.COMMAND.getPermission())) return;
                if (error) {
                    p.sendMessage(translateColor("&8&l| &7&o&nDebug / &c&o&n" + debugMessage));
                    ChapterFramework.STATIC_INSTANCE.getLogger().info("| Debug / Error - " + debugMessage);
                } else {
                    p.sendMessage(translateColor("&8&l| &7&o&nDebug / &7&o&n" + debugMessage));
                    ChapterFramework.STATIC_INSTANCE.getLogger().info("| Debug / " + debugMessage);
                }
            });
        }
    }
    /**
     *
     * @param string - 需要染色的文本
     * @return 变色好的句子
     */
    default String translateColor(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     *
     * 通过 Title 显示文本
     *
     * @param player - 玩家
     * @param title - 主标题
     * @param subtitle - 副标题
     * @param fadeIn - 淡入时间
     * @param stay - 停留时间
     * @param fadeOut - 淡出时间
     */
    default void byTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut){
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     *
     * 通过 ActionBar 显示文本
     *
     * @param player - 玩家
     * @param string - 显示内容
     */
    default void byActionBar(Player player, String string){
        PacketContainer pc = new PacketContainer(PacketType.Play.Server.CHAT);
        pc.getBytes().write(0, EnumWrappers.ChatType.GAME_INFO.getId());
        pc.getChatComponents().write(0, WrappedChatComponent.fromText(string));

        try {
            ChapterFramework.STATIC_INSTANCE.protocolManager().sendServerPacket(player, pc);
        } catch (InvocationTargetException var4) {
            var4.printStackTrace();
        }
    }

    /**
     *
     * 通过 BossBar 显示文本
     *
     * @param player - 玩家
     * @param string - 显示内容
     */
    default void byBossBar(Player player, String string){
        PacketContainer pc = new PacketContainer(PacketType.Play.Server.BOSS);
        pc.getBytes().write(0, EnumWrappers.ChatType.CHAT.getId());
        pc.getChatComponents().write(0, WrappedChatComponent.fromText(string));

        try {
            ChapterFramework.STATIC_INSTANCE.protocolManager().sendServerPacket(player, pc);
        } catch (InvocationTargetException var4) {
            var4.printStackTrace();
        }
    }

}
