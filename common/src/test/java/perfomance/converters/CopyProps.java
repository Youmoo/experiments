package perfomance.converters;

import functional.Generator;
import functional.Generified;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import util.Converters;

/**
 * @author youmoo
 * @since 2014-10-17 3:18 PM
 */
public class CopyProps {

    int amount = 100;

    @Test
    public void test() {
        int loop = 10;
        while (loop-- > 0) {
            System.out.println("#########");
            testAsm1();
            testAsm2();
            testOrigin();
        }
    }

    /**
     * 使用asm进行bean属性复制,速度是传统reflection的3~6倍左右
     */
    @Test
    public void testAsm1() {
        Generified.measure("asm1", amount, new Generified.Action() {
            @Override
            public void act(Object o) {
                Converters.convert(Vo.class, new Model(), new Generator<Vo>() {
                    @Override
                    public Vo generate() {
                        return new Vo();
                    }
                });
            }
        });
    }

    /**
     * 减少generator生成次数,提升2倍速度
     */
    @Test
    public void testAsm2() {
        Generified.measure("asm2", amount, new Generified.Action() {
            Generator<Vo> generator = new Generator<Vo>() {
                @Override
                public Vo generate() {
                    return new Vo();
                }
            };

            @Override
            public void act(Object o) {
                Converters.convert(Vo.class, new Model(), generator);
            }
        });
    }

    @Test
    public void testOrigin() {

        Generified.measure("refl", amount, new Generified.Action() {
            @Override
            public void act(Object o) {
                try {
                    BeanUtils.copyProperties(new Vo(), new Model());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
