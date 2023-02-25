package me.sakuratao.chapterframework.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class NMSUtil {
    private static String version;

    static {
        try {
            version = getNMSVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NMSUtil() {
    }

    @NotNull
    public static String getNMSVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    public static boolean isSupported(){
        String[] split = version.split("_");
        if (Integer.parseInt(split[1]) < 19)
            return false;
        return version.startsWith("v1_19_R2");
    }

}
