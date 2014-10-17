package util;

import com.esotericsoftware.reflectasm.MethodAccess;
import factory.ObjectFactory;
import functional.Generator;

import java.util.List;

/**
 * @author youmoo
 * @since 2014-10-17 7:16 PM
 */
public class Converters {

    /**
     * 将T类型对象转换成R类型对像
     *
     * @param clz       结果对象
     * @param orign     原始对象
     * @param generator 原始对象生成器
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> R convert(Class<R> clz, T orign, Generator<R> generator) {

        MethodAccess destMethods = MethodAccess.get(clz);
        String[] methods = destMethods.getMethodNames();

        MethodAccess originMethods = MethodAccess.get(orign.getClass());

        R t = generator.generate();

        for (int i = 0, len = methods.length; i < len; i++) {
            String method = methods[i];
            if (method.startsWith("set")) {
                destMethods.invoke(t, i, originMethods.invoke(orign, "get" + method.substring(3)));
            }
        }


        return t;
    }


    /**
     * 批量转换
     */
    public static <R, T> List<R> convert(Class<R> clz, List<T> origins, Generator<R> generator) {

        if (origins == null || origins.size() == 0) {
            return null;
        }

        MethodAccess destMethods = MethodAccess.get(clz);
        String[] methods = destMethods.getMethodNames();

        MethodAccess originMethods = MethodAccess.get(origins.get(0).getClass());

        List<R> result = ObjectFactory.list(origins.size());
        int size = origins.size();
        while (size-- > 0) {
            result.add(generator.generate());
        }
        for (int i = 0, len = methods.length; i < len; i++) {
            String method = methods[i];
            if (method.startsWith("set")) {
                size = result.size();
                int indexOfGetter = originMethods.getIndex("get" + method.substring(3));
                while (size-- > 0) {
                    destMethods.invoke(result.get(size), i, originMethods.invoke(origins.get(size), indexOfGetter));
                }
            }
        }

        return result;
    }

}
