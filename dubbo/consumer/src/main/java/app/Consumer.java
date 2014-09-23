package app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DemoService;

/**
 * 服务消费者
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-consumer.xml"});
        DemoService service = (DemoService) context.getBean("demoService");/*获取服务*/
        String result = service.helloDubbo();
        System.out.println("result is " + result);
    }
}
