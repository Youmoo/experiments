package functional;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 允许对String,Number,Date类型等进行排序
 *
 * @autor youmoo
 * @since 2014-04-17 下午4:38
 */
public class AnySorter {
    public static final Log logger = LogFactory.getLog(AnySorter.class);
    public static final String ORDER_DESC = "desc";/*降序*/
    public static final String ORDER_ASC = "asc";/*升序*/

    /**
     * 根据集合中元素的某一属性值，对集合进行排序
     * 注意，属性值必须是兼容于{@code Comparable}的类型
     *
     * @param list      待排序集合
     * @param fieldName 属性值
     * @param descOrAsc 升序还是降序,取值为 asc 或 desc
     */
    public static void sort(List<?> list, String fieldName, String descOrAsc) {
        if (list == null || list.size() < 2) {
            return;
        }
        if (fieldName == null || (fieldName = fieldName.trim()).length() < 1) {
            throw new IllegalArgumentException("排序属性名不能为空。fieldName:\t" + fieldName);
        }
        try {
            Class<?> clz = list.get(0).getClass();
            String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + (fieldName.length() > 1 ? fieldName.substring(1) : "");

//            Method getter = clz.getDeclaredMethod(methodName);/*擦，得不到父类的public方法*/
            Method getter = MethodUtils.getAccessibleMethod(clz, methodName, new Class[]{});
            if (getter == null) {
                throw new IllegalArgumentException(clz + "不存在该方法->" + methodName);
            }
            if (!Comparable.class.isAssignableFrom(getter.getReturnType())) {
                logger.error("排序属性必须是Comparable的子类。fieldName:\t" + fieldName);
                return;
            }
            int order = ORDER_ASC.equals(descOrAsc) ? 1 : -1;
            doSort(list, getter, order);
        } catch (Exception e) {
            logger.error("排序出错。" + e);
        }
    }

    private static void doSort(List<?> list, final Method getter, final int order) {
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                try {
                    o1 = getter.invoke(o1, null);
                    o2 = getter.invoke(o2, null);
                    return ((Comparable) o1).compareTo(o2) * order;
                } catch (Exception e) {
                    logger.error("在尝试利用反射进行集合排序时出错。" + e);
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

}
