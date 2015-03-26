package functional;

import factory.ObjectFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

import static util.NumberUtils.*;

/**
 * 函数式，仿js中的Array.prototype并将一些常用操作泛化
 *
 * @author youmoo
 * @since 2014-04-15 下午8:19
 */
public class Generified {

    public static final Log logger = LogFactory.getLog(Generified.class);

    /**
     * 过滤出符合条件的所有元素
     */
    public static <E> List<E> filter(Iterable<E> iterable, Predictor predictor) {
        List<E> result = ObjectFactory.list();
        for (E e : iterable) {
            if (predictor.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * 过滤出符合条件的第一个元素
     */
    public static <E> E first(Iterable<E> iterable, Predictor predictor) {
        List<E> result = ObjectFactory.list();
        for (E e : iterable) {
            if (predictor.test(e)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 过滤出符合条件的最后一个元素
     */
    public static <E> E last(Iterable<E> iterable, Predictor predictor) {
        E result = null;
        for (E e : iterable) {
            if (predictor.test(e)) {
                result = e;
            }
        }
        return result;
    }

    /**
     * 遍历并处理集合中的每个元素
     */
    public static <E> void forEach(Iterable<E> iterable, Action<E> action) {
        for (E e : iterable) {
            action.act(e);
        }
    }

    /**
     * 将集合元素按照某一特点分组，类似sql中的group by
     */
    public static <E> Map<String, List<E>> group(Iterable<E> iterable, KeyGenerator<E> keyGenerator) {
        Map<String, List<E>> result = ObjectFactory.map();

        if (iterable != null) {
            for (E e : iterable) {
                String key = keyGenerator.generate(e);
                List<E> prev = result.get(key);
                if (prev == null) {
                    prev = ObjectFactory.list();
                    result.put(key, prev);
                }
                prev.add(e);
            }
        }

        return result;
    }

    public static <K, V> Map<K, List<V>> group(Iterable<V> iterable, KeyFactory<K, V> keyFactory) {
        Map<K, List<V>> result = ObjectFactory.map();

        for (V value : iterable) {
            K key = keyFactory.makeKey(value);
            List<V> prev = result.get(key);
            if (prev == null) {
                prev = ObjectFactory.list();
                result.put(key, prev);
            }
            prev.add(value);
        }

        return result;
    }

    /**
     * 将集合转换为从键到元素的映射
     */
    public static <E> Map<String, E> groupAsSingleValueMap(Iterable<E> iterable, KeyGenerator<E> keyGenerator) {
        Map<String, E> result = ObjectFactory.map();

        for (E e : iterable) {
            String key = keyGenerator.generate(e);
            if (result.containsKey(key)) {
                throw new IllegalStateException("duplicat value found for key :" + key);
            }
            result.put(key, e);
        }

        return result;
    }

    /**
     * 将集合元素进行转型后分组
     */
    public static <R, E> Map<String, List<R>> group(Iterable<E> iterable, KeyValueGenerator<R, E> keyValueGenerator) {
        Map<String, List<R>> result = ObjectFactory.map();

        for (E e : iterable) {
            String key = keyValueGenerator.generateKey(e);
            R value = keyValueGenerator.generateValue(e);
            List<R> prev = result.get(key);
            if (prev == null) {
                prev = ObjectFactory.list();
                result.put(key, prev);
            }

            prev.add(value);
        }

        return result;
    }


    /**
     * 将集合元素进行分组汇总操作
     * 示例：
     * 对于集合[{a:1,b:2},{a:3,b:4}],以a为key进行分组汇总操作则返加{a:{a:4,b:6}}
     */
    public static <E> Map<String, E> groupAndSum(Iterable<E> iterable, KeyGenerator<E> keyGenerator) {
        Map<String, E> result = ObjectFactory.map();

        Map<String, List<E>> grouped = group(iterable, keyGenerator);/*先分组*/

        for (Map.Entry<String, List<E>> entry : grouped.entrySet()) {
            E summed = sumValues(entry.getValue());/*再对每组总总值*/
            result.put(entry.getKey(), summed);
        }

        return result;
    }

    public static <K, V> Map<K, V> groupAndSum(Iterable<V> iterable, KeyFactory<K, V> keyFactory) {
        Map<K, V> result = ObjectFactory.map();

        Map<K, List<V>> grouped = group(iterable, keyFactory);/*先分组*/

        for (Map.Entry<K, List<V>> entry : grouped.entrySet()) {
            V summed = sumValues(entry.getValue());/*再对每组总总值*/
            result.put(entry.getKey(), summed);
        }

        return result;
    }

    public static <K, V> List<V> groupAndSumAsList(Iterable<V> iterable, KeyFactory<K, V> keyFactory) {

        Map<K, List<V>> grouped = group(iterable, keyFactory);/*先分组*/
        List<V> result = ObjectFactory.list(grouped.size());


        for (Map.Entry<K, List<V>> entry : grouped.entrySet()) {
            V summed = sumValues(entry.getValue());/*再对每组总总值*/
            result.add(summed);
        }

        return result;
    }

    /**
     * 计算总值，E必须要有一个默认的构造参数
     *
     * @param list 如果为空，则返回null
     * @param <E>  元素类型，通常是一个entity
     * @return 对于对对象[{a:1,b:2},{a:3,b:4}]，返回{a:4,b:6}
     */
    public static <E> E sumValues(List<E> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        try {
            Method getter, setter;
            Class modelClz = list.get(0).getClass(), type;
            BeanInfo info = Introspector.getBeanInfo(modelClz, Object.class);
            PropertyDescriptor[] descriptors = info.getPropertyDescriptors();

            Object resultVo = modelClz.newInstance();
            for (Object vo : list) {
                for (PropertyDescriptor descriptor : descriptors) {
                    getter = descriptor.getReadMethod();
                    setter = descriptor.getWriteMethod();
                    if (setter == null || getter == null) {
//                        logger.info(descriptor + "不存在getter或setter，忽略掉。");
                        continue;
                    }
                    type = getter.getReturnType();
                    Object prev = getter.invoke(resultVo, null);
                    Object cur = getter.invoke(vo, null);
                    try {
                        if (type == Double.class || type == Double.TYPE) {/*江总double值*/
                            setter.invoke(resultVo, sumDouble(prev, cur));
                        } else if (type == Integer.class || type == Integer.TYPE) {/*汇总int值*/
                            setter.invoke(resultVo, sumInt(prev, cur));
                        } else if (type == Long.class || type == Long.TYPE) {/*汇总long值*/
                            setter.invoke(resultVo, sumLong((Long) prev, (Long) cur));
                        } else {/*对于我们应用来说，一般是date或string类型，所以直接赋值即可*/
                            setter.invoke(resultVo, cur);
                        }
                    } catch (Exception e) {
                        logger.error("1、在汇总数据时出错，请检查原因。");
                        e.printStackTrace();
                    }
                }
            }
            return (E) resultVo;
        } catch (Exception e) {
            logger.error("2、在汇总数据时出错，请检查原因。");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 计算元素均值
     * 示例：
     * 对于元素{a:2,b:4},其对于2的均值为{a:1,b:2}
     */
    public static <E> E average(E devidend, int devisor) {
        if (devisor == 0) {
            throw new IllegalArgumentException("除数不能为0！");
        }
        try {
            Method getter, setter;
            Class modelClz = devidend.getClass(), type;
            BeanInfo info = Introspector.getBeanInfo(modelClz, Object.class);
            PropertyDescriptor[] descriptors = info.getPropertyDescriptors();

            Object resultVo = modelClz.newInstance();
            for (PropertyDescriptor descriptor : descriptors) {
                getter = descriptor.getReadMethod();
                setter = descriptor.getWriteMethod();
                if (setter == null || getter == null) {
                    continue;
                }
                type = getter.getReturnType();
                Object prev = getter.invoke(devidend, null);
                try {
                    if (type == Double.class || type == Double.TYPE) {
                        setter.invoke(resultVo, divide((Number) prev, devisor));
                    } else if (type == Integer.class || type == Integer.TYPE) {
                        setter.invoke(resultVo, Double.valueOf(divide((Number) prev, devisor)).intValue());
                    } else if (type == Long.class || type == Long.TYPE) {/*汇总long值*/
                        setter.invoke(resultVo, Double.valueOf(divide((Number) prev, devisor)).longValue());
                    } else {/*对于我们应用来说，一般是date或string类型，所以直接赋值即可*/
                        setter.invoke(resultVo, prev);
                    }
                } catch (Exception e) {
                    logger.error("1、在求数据均值时出错，请检查原因。");
                    e.printStackTrace();
                }

            }
            return (E) resultVo;
        } catch (Exception e) {
            logger.error("2、在求数据均值时出错，请检查原因。");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 求一个集合的均值
     * 示例：
     * 对于集合[{a:2,b:4},{a:4,b:2}]，对于2的均值返加{a:3,b:3}
     */
    public static <E> E average(List<E> toBeSummed, int devisor) {
        if (devisor == 0) {
            throw new IllegalArgumentException("除数不能为0！");
        }
        E devidend = sumValues(toBeSummed);
        return average(devidend, devisor);
    }

    public static <E extends Number> E sumValues(List<E> list, ValueGenerator<E> valueGenerator) {
        Double result = 0d;
        for (E e : list) {
            result += Double.valueOf(valueGenerator.generateValue(e).toString());
        }
        return (E) result;
    }

    /**
     * 将一个集合转换成另一个类型的集合
     */
    public static <R, E> List<R> transform(Iterable<E> iterable, Transformer<R, E> transformer) {
        List<R> result = ObjectFactory.list();
        for (E e : iterable) {
            result.add(transformer.transform(e));
        }
        return result;
    }

    /**
     * 目前用来承担从model到vo的转换工作
     *
     * @param iterable model集合
     * @param clz
     * @param <R>      vo类型
     * @param <E>      model类型
     * @return vo集合
     */
    public static <R, E> List<R> transform(Iterable<E> iterable, Class<R> clz) {
        if (iterable == null) {
            return null;
        }
        List<R> result = ObjectFactory.list();
        try {
            for (E e : iterable) {
                R dest = clz.newInstance();
                BeanUtils.copyProperties(dest, e);
                result.add(dest);
            }
            return result;
        } catch (Exception e) {
            logger.error("bean转换成vo出错。" + e);
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E reduce(Iterable<E> iterable, Reducer<E> reducer) {
        if (iterable == null) {
            return null;
        }
        Iterator<E> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        E result = iterator.next();
        while (iterator.hasNext()) {
            result = reducer.reduce(result, iterator.next());
        }
        return result;
    }

    public static <E> List<E> ensureNotNull(List<E> list) {
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public static <E> Set<E> ensureNotNull(Set<E> set) {
        return set == null ? Collections.EMPTY_SET : set;
    }

    public static <K, E> Map<K, E> ensureNotNull(Map<K, E> map) {
        return map == null ? Collections.EMPTY_MAP : map;
    }

    public static <K, E> E getWithDefault(Map<K, E> map, K key, E defaultValue) {
        E value = map.get(key);
        if (value == null) {
            value = defaultValue;
            map.put(key, value);
        }
        return value;
    }

    public static <E> Map<String, E> asMap(Iterable<E> iterable, KeyGenerator<E> keyGenerator) {
        Map<String, E> result = ObjectFactory.map();
        for (E e : iterable) {
            result.put(keyGenerator.generate(e), e);
        }
        return result;
    }

    public static <E, R> R copy(R dest, E src) {
        try {
            BeanUtils.copyProperties(dest, src);
            return dest;
        } catch (Exception e) {
            throw new RuntimeException("copy bean属性时出错", e);
        }
    }

    public static <E, R> List<R> copy(List<E> list, Class<R> clz) {
        List<R> result = ObjectFactory.list();
        R temp;
        for (E e : list) {
            result.add(copy(create(clz), e));
        }
        return result;
    }

    public static <E> E create(Class<E> clz) {

        try {
            return clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建对象实例时出错。", e);
        }

    }

    public static void measure(String name, int amount, Action action) {
        long time = -System.currentTimeMillis();
        while (amount-- != 0) {
            action.act(null);
        }
        System.out.println(name + "--耗时:" + (time + System.currentTimeMillis()));
    }

    /*key生成器*/
    public static interface KeyGenerator<E> {
        public String generate(E e);
    }

    /*对象工厂*/
    public static interface ElementFactory<E> {
        public E newInstance();
    }

    /*后置处理器*/
    public static interface PostProcessor<E> {
        public void proccess(E result, List<E> origin);
    }

    /*key/value生成器*/
    public static interface KeyValueGenerator<R, E> {
        public String generateKey(E e);

        public R generateValue(E e);
    }

    /*value生成器*/
    public static interface ValueGenerator<E> {
        public E generateValue(E e);
    }

    /*转换器*/
    public static interface Transformer<R, E> {
        public R transform(E e);
    }

    /*推导*/
    public static interface Reducer<E> {
        public E reduce(E e1, E e2);
    }

    /*元素操作*/
    public static interface Action<E> {
        public void act(E e);
    }

    /*元素生成工厂*/
    public static interface Generator<E> {
        public E produce();
    }
}
