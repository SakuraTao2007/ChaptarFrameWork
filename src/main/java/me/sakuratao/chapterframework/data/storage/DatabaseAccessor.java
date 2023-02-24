package me.sakuratao.chapterframework.data.storage;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import me.sakuratao.chapterframework.ChapterFramework;
import me.sakuratao.chapterframework.data.player.PlayerData;
import top.jingwenmc.spigotpie.common.instance.PieComponent;
import top.jingwenmc.spigotpie.common.instance.Wire;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

@PieComponent
public class DatabaseAccessor {
    ConnectionSource connectionSource;
    DataSource dataSource;

    @Getter
    Dao<PlayerData, String> playerDataDao;

    @Wire
    ChapterFramework plugin;

    public void reconnect() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        if(DatabaseConfiguration.USE_MYQSL) {
            hikariConfig.setJdbcUrl(DatabaseConfiguration.MYSQL_URL);
            hikariConfig.setUsername(DatabaseConfiguration.MYSQL_USERNAME);
            hikariConfig.setPassword(DatabaseConfiguration.MYSQL_PASSWORD);
        } else {
            File file = new File(plugin.getDataFolder(),"database");
            hikariConfig.setDriverClassName("org.h2.Driver");
            hikariConfig.setJdbcUrl("jdbc:h2:file:"+ file.getAbsolutePath());
        }
        dataSource = new HikariDataSource(hikariConfig);
        connectionSource = new DataSourceConnectionSource(dataSource, DatabaseConfiguration.MYSQL_URL);
        playerDataDao = createDao(PlayerData.class,"1");
    }

    public void close() throws Exception {
        connectionSource.close();
    }

    public <K,ID> Dao<K,ID> createDao(Class<K> kClass,ID testValue) throws SQLException {
        Dao<K,ID> dao;
        try {
            //建立表关系
            dao = DaoManager.createDao(connectionSource, kClass);
            dao.idExists(testValue);//测试
        } catch (Exception e) {
            //未找到就新建表
            TableUtils.createTableIfNotExists(connectionSource, kClass);
            dao = DaoManager.createDao(connectionSource, kClass);
        }
        return dao;
    }
}