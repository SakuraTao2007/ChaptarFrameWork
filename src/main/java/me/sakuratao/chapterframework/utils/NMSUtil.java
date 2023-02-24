package me.sakuratao.chapterframework.utils;

import org.bukkit.Bukkit;

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

    public static String getNMSVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    public static boolean isSupported(){
        if (version == null)
            return false;
        return version.startsWith("v1_19_R2");
    }

}
