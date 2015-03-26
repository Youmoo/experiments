package perfomance;

import org.junit.Test;

/**
 * 比对一下性能
 * 结果分析：new和gen结果相当,gen略快，refl慢了1到8倍
 *
 * @author youmoo
 * @since 2014-05-08 下午5:25
 */
public class PerformanceTest {

    public static final int AMOUNT = 10000000;

    @Test
    public void test() throws Exception {
        long timeConsumed = -System.currentTimeMillis();
        testNew();/**/
        System.out.println("new:\t" + (timeConsumed + System.currentTimeMillis()));
        timeConsumed = -System.currentTimeMillis();
        testGen();
        System.out.println("gen:\t" + (timeConsumed + System.currentTimeMillis()));
        timeConsumed = -System.currentTimeMillis();
        testReflection();
        System.out.println("refl:\t" + (timeConsumed + System.currentTimeMillis()));
    }

    public void testNew() {
        for (int i = AMOUNT; i-- != 0; ) {
            new ValueHolder();
        }
    }

    public void testGen() {
        Generator<ValueHolder> generator = new Generator<ValueHolder>() {/*单例化*/
            @Override
            public ValueHolder gen() {
                return new ValueHolder();
            }
        };
        for (int i = AMOUNT; i-- != 0; ) {
            generator.gen();
        }
    }

    public void testReflection() throws Exception {
        Class<ValueHolder> valueHolderClass = ValueHolder.class;
        for (int i = AMOUNT; i-- != 0; ) {
            valueHolderClass.newInstance();
        }
    }


    public static interface Generator<E> {
        E gen();
    }
}
