package precessor;

import annotation.Hello;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Date;

/**
 * @autor youmoo
 * @since 2014-04-08 10:59 PM
 */
public class DemoProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (s.equals("hello")) {
            System.out.println("before init");
            ((Hello) o).setModified(new Date());
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (s.equals("hello")) {
            System.out.println("after init");
        }
        return o;
    }
}
