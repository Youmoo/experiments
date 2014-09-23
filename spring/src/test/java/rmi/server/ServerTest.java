package rmi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @autor youmoo
 * @since 2014-04-06 5:40 PM
 */
public class ServerTest {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring/spring-rmi-server.xml");
    }
}
