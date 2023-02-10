package me.sakuratao.chapterframework.handler;

import lombok.Getter;
import me.sakuratao.chapterframework.data.player.PlayerData;
import org.bukkit.entity.Player;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@PieComponent
public class PlayerDataHandler {

    @Wire
    public static PlayerDataHandler STATIC_INSTANCE;

    private final ConcurrentMap<UUID, PlayerData> playerDataMap =  new ConcurrentHashMap<>();

    public PlayerData getPlayerData(Player player){
        return playerDataMap.get(player.getUniqueId());
    }

    public PlayerData getPlayerData(UUID uuid){
        return playerDataMap.get(uuid);
    }

    public boolean putPlayerData(Player player, PlayerData playerData) {
        playerDataMap.put(player.getUniqueId(), playerData);
        return true;
    }

    public boolean putPlayerData(UUID uuid, PlayerData playerData) {
        playerDataMap.put(uuid, playerData);
        return true;
    }

    public boolean removePlayerData(Player player, PlayerData playerData) {
        playerDataMap.remove(player.getUniqueId(), playerData);
        return true;
    }

    public boolean removePlayerData(UUID uuid, PlayerData playerData) {
        playerDataMap.remove(uuid, playerData);
        return true;
    }

    public ConcurrentMap<UUID, PlayerData> mapObject = playerDataMap;

}
