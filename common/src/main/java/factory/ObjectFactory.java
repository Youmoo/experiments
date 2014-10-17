package factory;

import java.util.*;

/**
 * 对象工厂，用来生成常用对象，尤其是集合类
 *
 * @author youmoo
 * @version 1.0 2013-12-6
 */
public class ObjectFactory {
    /**
     * 获取一个HashMap实例
     *
     * @param <K> key的类型
     * @param <V> value的类型
     * @return 一个HashMap实例
     */
    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    /**
     * 获取一个ArrayList实例
     *
     * @param <T> 集合元素类型
     * @return 一个ArrayList实例
     */
    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    /**
     * 获取一个ArrayList实例
     *
     * @param <T> 集合元素类型
     * @return 一个ArrayList实例
     */
    public static <T> List<T> list(int size) {
        return new ArrayList<T>(size);
    }

    /**
     * 获取一个HashSet实例
     *
     * @param <T> 集合元素类型
     * @return 一个HashSet实例
     */
    public static <T> Set<T> set() {
        return new HashSet<T>();
    }
}
