package me.sakuratao.storychapterframework.utils.helper;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.sakuratao.storychapterframework.StoryChapterFrameWork;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public interface MessageHelper {

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
            StoryChapterFrameWork.staticInstance.plibapi.sendServerPacket(player, pc);
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
            StoryChapterFrameWork.staticInstance.plibapi.sendServerPacket(player, pc);
        } catch (InvocationTargetException var4) {
            var4.printStackTrace();
        }
    }

}
