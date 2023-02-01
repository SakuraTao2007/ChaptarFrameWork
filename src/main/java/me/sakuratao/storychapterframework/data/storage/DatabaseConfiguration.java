package me.sakuratao.storychapterframework.data.storage;

import top.jingwenmc.spigotpie.common.configuration.BaseConfiguration;
import top.jingwenmc.spigotpie.common.configuration.Configuration;
import top.jingwenmc.spigotpie.common.configuration.ConfigurationFile;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
@ConfigurationFile("database.yml")
public class DatabaseConfiguration extends BaseConfiguration {
    @Configuration("use_mysql")
    public static boolean useMysql = false;

    @Configuration(value = "mysql.url")
    public static String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/databaseName";
    @Configuration(value = "mysql.username")
    public static String mysqlUsername = "root";
    @Configuration(value = "mysql.password")
    public static String mysqlPassword = "123456";

}
