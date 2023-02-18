package me.sakuratao.chapterframework.handler;

import lombok.Getter;
import lombok.NonNull;
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

    public PlayerData getPlayerData(@NonNull Player player){
        return playerDataMap.get(player.getUniqueId());
    }

    public PlayerData getPlayerData(@NonNull UUID uuid){
        return playerDataMap.get(uuid);
    }

    public boolean putPlayerData(@NonNull Player player,@NonNull PlayerData playerData) {
        playerDataMap.put(player.getUniqueId(), playerData);
        playerData.decodeProgress(playerData.getProgressEncode());
        return true;
    }

    public boolean putPlayerData(@NonNull UUID uuid,@NonNull PlayerData playerData) {
        playerDataMap.put(uuid, playerData);
        playerData.decodeProgress(playerData.getProgressEncode());
        return true;
    }

    public boolean putPlayerData(@NonNull PlayerData playerData) {
        playerDataMap.put(playerData.getPlayerUuid(), playerData);
        playerData.decodeProgress(playerData.getProgressEncode());
        return true;
    }

    public boolean removePlayerData(@NonNull Player player) {
        playerDataMap.remove(player.getUniqueId());
        return true;
    }

    public boolean removePlayerData(@NonNull UUID uuid) {
        playerDataMap.remove(uuid);
        return true;
    }

    public ConcurrentMap<UUID, PlayerData> mapObject = playerDataMap;

}
