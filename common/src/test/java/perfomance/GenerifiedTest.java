package perfomance;

import factory.ObjectFactory;
import functional.Generified;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author youmoo
 * @since 2014-05-05 上午10:52
 */
public class GenerifiedTest {
    @Test
    public void testAverage() throws Exception {
        ValueHolder holder = new ValueHolder(10, 20d);
        holder = Generified.average(holder, 5);
    }

    @Test
    public void testGroup() {
        List<ValueHolder> holders = ObjectFactory.list();
        holders.add(new ValueHolder("a", 1, 1d, new Date()));
        holders.add(new ValueHolder("a", 2, 2d, new Date()));
        Map<String, List<ValueHolder>> map = Generified.group(holders, new Generified.KeyGenerator<ValueHolder>() {
            @Override
            public String generate(ValueHolder valueHolder) {
                return valueHolder.getUsername();
            }
        });
    }
}
