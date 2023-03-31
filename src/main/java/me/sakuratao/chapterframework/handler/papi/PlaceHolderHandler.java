package me.sakuratao.chapterframework.handler.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.sakuratao.chapterframework.ChapterFramework;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

@PieComponent
public class PlaceHolderHandler extends PlaceholderExpansion {

    @Wire
    ChapterFramework chapterFramework;

    @Override
    public @NotNull String getIdentifier() {
        return "cfw";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SakuraTao, jingwenMC";
    }

    @Override
    public @NotNull String getVersion() {
        return chapterFramework.getDescription().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {

        return switch (params) {
            case "version" -> chapterFramework.getDescription().getVersion();
            case "name" -> chapterFramework.getDescription().getName();
            default -> "null";
        };

    }
}
