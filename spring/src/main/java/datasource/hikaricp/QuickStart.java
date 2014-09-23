package datasource.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/brettwooldridge/HikariCP
 *
 * @author youmoo
 * @since 2014-09-16 10:28 AM
 */
public class QuickStart {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDs());
        List<Map<String, Object>> users = jdbcTemplate.queryForList("select * from tb_user");
    }


    public static DataSource getDs() {
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
