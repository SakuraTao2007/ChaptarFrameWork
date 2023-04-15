package me.sakuratao.chapterframework.data.cache;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.sakuratao.chapterframework.tasks.RegularLoopExecutionTask;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Data
@NoArgsConstructor
public class CacheData {

    final ConcurrentMap<Player, BukkitTask> bukkitTaskMap = new ConcurrentHashMap<>(); /* for regularLoopExecution */

    Map<Player, List<String>> printHistory = new HashMap<>();

    boolean PAPI = false;
    boolean PROTOCOL_LIB = false;
    boolean CITIZENS = false;

}
