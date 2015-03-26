package rmi.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rmi.server.HelloRmiService;
import rmi.server.User;

/**
 * @author youmoo
 * @since 2014-04-06 5:41 PM
 */
public class ClientTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rmi-client.xml");

        HelloRmiService service = (HelloRmiService) context.getBean("rmiClient", HelloRmiService.class);
        System.out.println(service.getGreeting());
        User user = null;
        while (true) {
            user = service.getUser();
        }
    }
}
