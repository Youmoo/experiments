package perfomance.converters;

import factory.ObjectFactory;
import functional.Generator;
import functional.Generified;
import org.junit.Test;
import util.Converters;

import java.util.Date;
import java.util.List;

/**
 * 批量转换model的性能比较
 *
 * @author youmoo
 * @since 2014-10-17 8:04 PM
 */
public class BatchCopyProps {

    int amount = 1;
    int size = 100;
    List<Model> models = getOriginData(size);

    /**
     * asm比传统的反射传值快10倍左右
     */
    @Test
    public void testAsm() {
        Generified.measure("asm", amount, new Generified.Action() {
            @Override
            public void act(Object o) {
                List<Vo> vos = Converters.convert(Vo.class, models, new Generator<Vo>() {
                    @Override
                    public Vo generate() {
                        return new Vo();
                    }
                });
            }
        });
    }

    @Test
    public void test() {
        int loop = 10;
        while (loop-- > 0) {
            System.out.println("##############");
            testAsm();
            testRefl();

        }
    }


    @Test
    public void testRefl() {
        Generified.measure("refl", amount, new Generified.Action() {
            @Override
            public void act(Object o) {
                Generified.transform(models, Vo.class);
            }
        });
    }


    public List<Model> getOriginData(int size) {
        List<Model> models = ObjectFactory.list(size);
        while (size-- != 0) {
            models.add(new Model("--" + size, new Date(), Math.random()));
        }
        return models;
    }
}
