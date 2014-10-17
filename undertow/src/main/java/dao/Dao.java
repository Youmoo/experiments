package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exception.DaoException;

import javax.sql.DataSource;
import java.sql.*;

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

    /**
     * 设置参数值
     */
    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    /**
     * 设置参数值
     */
    protected void fillStatement(PreparedStatement stmt, Object[] params)
            throws SQLException {

        if (params == null) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                stmt.setObject(i + 1, params[i]);
            } else {
                stmt.setNull(i + 1, Types.OTHER);
            }
        }
    }
}
