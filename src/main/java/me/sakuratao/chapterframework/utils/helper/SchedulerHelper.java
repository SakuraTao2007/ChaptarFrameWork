package me.sakuratao.chapterframework.utils.helper;

import me.sakuratao.chapterframework.ChapterFramework;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
public interface SchedulerHelper {
    
    /**
     *
     * 定时执行 task
     *
     * @param runnable - 需要提供的 Runnable
     * @param delay - 倒时后延迟多少 ticks 执行
     * @param interval - 每次执行间隔 (ticks)
     */
    default void taskTimer(Runnable runnable, long delay, long interval) {
         Bukkit.getScheduler().runTaskTimer(ChapterFramework.STATIC_INSTANCE, runnable, delay, interval);
    }

    /**
     *
     * 异步定时执行 task
     *
     * @param runnable - 需要提供的 Runnable
     * @param delay - 倒时后延迟多少 ticks 执行
     * @param interval - 每次执行间隔 (ticks)
     */
    default void taskTimerAsync(Runnable runnable, long delay, long interval) {
         Bukkit.getScheduler().runTaskTimerAsynchronously(ChapterFramework.STATIC_INSTANCE, runnable, delay, interval);
    }

    default BukkitTask taskTimerAsync(Player player, Runnable runnable, long delay, long interval) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(ChapterFramework.STATIC_INSTANCE, runnable, delay, interval);
    }

    /**
     *
     * 执行 task
     *
     * @param runnable - 需要提供的 Runnable
     */
    default void task(Runnable runnable) {
         Bukkit.getScheduler().runTask(ChapterFramework.STATIC_INSTANCE, runnable);
    }

    /**
     *
     * 执行异步 task
     *
     * @param runnable - 需要提供的 Runnable
     */
    default void taskAsync(Runnable runnable) {
         Bukkit.getScheduler().runTaskAsynchronously(ChapterFramework.STATIC_INSTANCE, runnable);
    }

    /**
     *
     * 执行延迟 task
     *
     * @param runnable - 需要提供的 Runnable
     * @param delay - 延迟 ticks
     */
    default void taskLater(Runnable runnable, long delay) {
         Bukkit.getScheduler().runTaskLater(ChapterFramework.STATIC_INSTANCE, runnable, delay);
    }

    /**
     *
     * 执行异步延迟 task
     *
     * @param runnable - 需要提供的 Runnable
     * @param delay - 延迟 ticks
     */
    default void taskLaterAsync(Runnable runnable, long delay) {
         Bukkit.getScheduler().runTaskLaterAsynchronously(ChapterFramework.STATIC_INSTANCE, runnable, delay);
    }
    
}
