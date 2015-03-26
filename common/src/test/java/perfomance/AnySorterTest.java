package perfomance;

import factory.ObjectFactory;
import functional.AnySorter;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 排序测试
 *
 * @author youmoo
 * @since 2014-04-17 下午4:43
 */
public class AnySorterTest {
    List<ValueHolder> valueHolders = ObjectFactory.list();
    Date min = new Date();

    @Before
    public void prepare() {
        valueHolders.add(new ValueHolder("a", 1, 1.1, min));
        valueHolders.add(new ValueHolder("b", 2, 2.1, new Date()));
        valueHolders.add(new ValueHolder("c", 3, 3.1, new Date()));
        valueHolders.add(new ValueHolder("d", 4, 4.1, new Date()));
//        valueHolders.add(new ValueHolder(null, null, null, null));
    }

    @Test
    public void sortByString() {
        AnySorter.sort(valueHolders, "username", "asc");
        Assert.assertEquals(valueHolders.get(0).getUsername(), "a");
        AnySorter.sort(valueHolders, "username", "desc");
        Assert.assertEquals(valueHolders.get(0).getUsername(), "d");
    }

    @Test
    public void sortByInteger() {
        AnySorter.sort(valueHolders, "age", "asc");
        Assert.assertEquals(valueHolders.get(0).getAge(), new Integer(1));
        AnySorter.sort(valueHolders, "age", "desc");
        Assert.assertEquals(valueHolders.get(0).getAge(), new Integer(4));
    }

    @Test
    public void sortByDouble() {
        AnySorter.sort(valueHolders, "height", "asc");
        Assert.assertEquals(valueHolders.get(0).getHeight(), new Double(1.1));
        AnySorter.sort(valueHolders, "height", "desc");
        Assert.assertEquals(valueHolders.get(0).getHeight(), new Double(4.1));
    }


    @Test
    public void sortByDate() {
        AnySorter.sort(valueHolders, "birthday", "asc");
        Assert.assertEquals(valueHolders.get(0).getBirthday(), min);
        AnySorter.sort(valueHolders, "birthday", "desc");
        Assert.assertEquals(valueHolders.get(3).getBirthday(), min);
    }
}
