package provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 服务提供者
 */
public class Provider {
    public static void main(String[] args) throws Exception {

        new ClassPathXmlApplicationContext(new String[]{"dubbo-provider.xml"});

        System.out.println("服务已启动");

        //确保程序不退出
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                System.out.println(count++ + ":\thello provider");
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

}
