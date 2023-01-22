package me.sakuratao.storychapterframework.utils.helper;

import me.sakuratao.storychapterframework.StoryChapterFrameWork;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

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
         Bukkit.getScheduler().runTaskTimer(StoryChapterFrameWork.staticInstance, runnable, delay, interval);
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
         Bukkit.getScheduler().runTaskTimerAsynchronously(StoryChapterFrameWork.staticInstance, runnable, delay, interval);
    }

    /**
     *
     * 执行 task
     *
     * @param runnable - 需要提供的 Runnable
     */
    default void task(Runnable runnable) {
         Bukkit.getScheduler().runTask(StoryChapterFrameWork.staticInstance, runnable);
    }

    /**
     *
     * 执行异步 task
     *
     * @param runnable - 需要提供的 Runnable
     */
    default void taskAsync(Runnable runnable) {
         Bukkit.getScheduler().runTaskAsynchronously(StoryChapterFrameWork.staticInstance, runnable);
    }

    /**
     *
     * 执行延迟 task
     *
     * @param runnable - 需要提供的 Runnable
     * @param delay - 延迟 ticks
     */
    default void taskLater(Runnable runnable, long delay) {
         Bukkit.getScheduler().runTaskLater(StoryChapterFrameWork.staticInstance, runnable, delay);
    }

    /**
     *
     * 执行异步延迟 task
     *
     * @param runnable - 需要提供的 Runnable
     * @param delay - 延迟 ticks
     */
    default void taskLaterAsync(Runnable runnable, long delay) {
         Bukkit.getScheduler().runTaskLaterAsynchronously(StoryChapterFrameWork.staticInstance, runnable, delay);
    }
    
}
