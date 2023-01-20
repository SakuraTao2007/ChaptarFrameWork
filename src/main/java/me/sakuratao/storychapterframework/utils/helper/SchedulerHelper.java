package me.sakuratao.storychapterframework.utils.helper;

import me.sakuratao.storychapterframework.StoryChapterFrameWork;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public interface SchedulerHelper {

    default void taskTimer(Runnable runnable, long delay, long interval) {
         Bukkit.getScheduler().runTaskTimer(StoryChapterFrameWork.staticInstance, runnable, delay, interval);
    }

    default void taskTimerAsync(Runnable runnable, long delay, long interval) {
         Bukkit.getScheduler().runTaskTimerAsynchronously(StoryChapterFrameWork.staticInstance, runnable, delay, interval);
    }

    default void task(Runnable runnable) {
         Bukkit.getScheduler().runTask(StoryChapterFrameWork.staticInstance, runnable);
    }

    default void taskAsync(Runnable runnable) {
         Bukkit.getScheduler().runTaskAsynchronously(StoryChapterFrameWork.staticInstance, runnable);
    }

    default void taskLater(Runnable runnable, long delay) {
         Bukkit.getScheduler().runTaskLater(StoryChapterFrameWork.staticInstance, runnable, delay);
    }

    default void taskLaterAsync(Runnable runnable, long delay) {
         Bukkit.getScheduler().runTaskLaterAsynchronously(StoryChapterFrameWork.staticInstance, runnable, delay);
    }
    
}
