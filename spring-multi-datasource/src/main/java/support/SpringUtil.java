package support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring工具类。用来获取配置在spring中的对象
 * Created by lee on 13-12-29.
 */
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static void init(String... files) {
        new ClassPathXmlApplicationContext(files);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取给定名称的bean对象
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T get(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取给定类型的bean对象，注意，此bean对象的名称应该遵守java变量命名规范
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(Class<T> clazz) {
        String name = clazz.getSimpleName();
        name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        return get(name);
    }
}
