package me.sakuratao.chapterframework.utils;

public class ChatColor {

    public static String Head = org.bukkit.ChatColor.AQUA.toString();
    public static String Scramble = org.bukkit.ChatColor.MAGIC.toString();
    public static String Bold = org.bukkit.ChatColor.BOLD.toString();
    public static String Strike = org.bukkit.ChatColor.STRIKETHROUGH.toString();
    public static String Line = org.bukkit.ChatColor.UNDERLINE.toString();
    public static String Italics = org.bukkit.ChatColor.ITALIC.toString();
    public static String Reset = org.bukkit.ChatColor.WHITE.toString();
    public static String Aqua = org.bukkit.ChatColor.AQUA.toString();
    public static String Black = org.bukkit.ChatColor.BLACK.toString();
    public static String Blue = org.bukkit.ChatColor.BLUE.toString();
    public static String DAqua = org.bukkit.ChatColor.DARK_AQUA.toString();
    public static String DBlue = org.bukkit.ChatColor.DARK_BLUE.toString();
    public static String DGray = org.bukkit.ChatColor.DARK_GRAY.toString();
    public static String DGreen = org.bukkit.ChatColor.DARK_GREEN.toString();
    public static String DPurple = org.bukkit.ChatColor.DARK_PURPLE.toString();
    public static String DRed = org.bukkit.ChatColor.DARK_RED.toString();
    public static String Gold = org.bukkit.ChatColor.GOLD.toString();
    public static String Gray = org.bukkit.ChatColor.GRAY.toString();
    public static String Green = org.bukkit.ChatColor.GREEN.toString();
    public static String Purple = org.bukkit.ChatColor.LIGHT_PURPLE.toString();
    public static String Red = org.bukkit.ChatColor.RED.toString();
    public static String White = org.bukkit.ChatColor.WHITE.toString();
    public static String Yellow = org.bukkit.ChatColor.YELLOW.toString();

    public static String strip(String t) {
        return org.bukkit.ChatColor.stripColor(t);
    }

    public static String translate(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }


}
