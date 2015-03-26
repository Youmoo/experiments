package mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j;
import mybatis.domain.User;
import mybatis.mapper.UserMapper;
import mybatis.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Random;

/**
 * @author youmoo
 * @since 2015-03-26 1:13 PM
 */
@Log4j
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
public class Application {

    @Component
    static class Runner implements CommandLineRunner {
        @Autowired
        private UserService userService;

        @Override
        public void run(String... args) throws Exception {
            User user = userService.getUser(123L);
            User anotherUser = userService.anotherGetUser(123L);
            userService.insertMany();//测试事务
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MapperFactoryBean<UserMapper> userMapper(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<UserMapper> mapperMapperFactoryBean = new MapperFactoryBean<UserMapper>();
        mapperMapperFactoryBean.setMapperInterface(UserMapper.class);
        mapperMapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);

        return mapperMapperFactoryBean;
    }

    /**
     * 可以注入到dao层中
     */
    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sessionTemplate;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    /**
     * 事务配置
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    /**
     * 数据源配置
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", "dbtest1");
        config.addDataSourceProperty("user", "bart");
        config.addDataSourceProperty("password", "51mp50n");

        return new HikariDataSource(config);
    }
}
