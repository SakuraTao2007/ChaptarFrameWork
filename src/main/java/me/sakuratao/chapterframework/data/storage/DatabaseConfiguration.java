package me.sakuratao.chapterframework.data.storage;

import top.jingwenmc.spigotpie.common.configuration.BaseConfiguration;
import top.jingwenmc.spigotpie.common.configuration.Configuration;
import top.jingwenmc.spigotpie.common.configuration.ConfigurationFile;
import top.jingwenmc.spigotpie.common.instance.PieComponent;

@PieComponent
@ConfigurationFile("database.yml")
public class DatabaseConfiguration extends BaseConfiguration {
    @Configuration("use_mysql")
    public static boolean USE_MYQSL = false;

    @Configuration(value = "mysql.url")
    public static String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/databaseName";
    @Configuration(value = "mysql.username")
    public static String MYSQL_USERNAME = "root";
    @Configuration(value = "mysql.password")
    public static String MYSQL_PASSWORD = "123456";

}
