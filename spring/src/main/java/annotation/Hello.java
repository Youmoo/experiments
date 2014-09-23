package annotation;

import org.springframework.stereotype.Component;
import support.SpringUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

/**
 * 演示初始化顺序
 */
@Component
public class Hello {

    Date modified;

    public Hello() {
        System.out.println("-- constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("-- init");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("-- cleanup");
    }

    public static void main(String[] args) {
        SpringUtil.init("/spring/spring-bean.xml");
        Hello hello = SpringUtil.get(Hello.class);
        System.out.println(hello.modified);
    }

    public void setModified(Date modified) {
        System.out.println("set modfied");
        this.modified = modified;
    }
}
