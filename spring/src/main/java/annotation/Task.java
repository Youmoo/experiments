package annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import support.SpringUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author youmoo
 * @since 2014-09-03 下午1:07
 */
@Component
public class Task {

    /**
     * 方法执行完成5秒后，再次执行
     */
    @Scheduled(fixedDelay = 5000)
    public void printWithDelay() throws Exception {
        System.out.println("delay:\t" + System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(6);
    }

    /**
     * 方法每5秒执行一次
     *
     * @throws Exception
     */
    @Scheduled(fixedRate = 5000)
    public void printWithRate() throws Exception {
        System.out.println("rate:\t" + System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(6);
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void printWithCron() {
        System.out.println("cron:\t" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SpringUtil.init("/spring/spring-bean.xml");
    }
}
