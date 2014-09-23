package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exception.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author youmoo
 * @since 2014-09-17 11:03 AM
 */
public class Dao {
    protected final static DataSource ds = getDs();

    public Connection getConn() {
        try {
            return ds.getConnection();
        } catch (Exception e) {
            throw new DaoException("fail to get connection ", e);
        }
    }

    public void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new DaoException("fail to close connection ", e);
        }
    }

    private static DataSource getDs() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", "dbtest1");
        config.addDataSourceProperty("user", "bart");
        config.addDataSourceProperty("password", "51mp50n");

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
