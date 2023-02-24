package me.sakuratao.chapterframework.data.cache;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.sakuratao.chapterframework.tasks.RegularLoopExecutionTask;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Data
@NoArgsConstructor
public class CacheData {

    final ConcurrentMap<Player, BukkitTask> bukkitTaskMap = new ConcurrentHashMap<>(); /* for regularLoopExecution */

    boolean PAPI = false;
    boolean PROTOCOL_LIB = false;

}
