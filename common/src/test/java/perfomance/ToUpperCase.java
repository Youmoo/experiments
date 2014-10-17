package perfomance;

import functional.Generified;
import org.junit.Test;

/**
 * 测试单个字母转换为大写时的性能
 * Character.toUpperCase快于String.toUpperCase10倍以上
 * menual check稍快于Character.toUpperCase
 *
 * @author youmoo
 * @since 2014-09-13 5:31 PM
 */
public class ToUpperCase {
    String str = "hello world !";

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {//
            doAction(action1, "String.toUpperCase");
            doAction(action2, "Character.toUpperCase");
            doAction(action3, "manual check");
        }

    }

    public void doAction(Generified.Action<Void> action, String type) {
        Generified.measure("uppercase", 1000, action);
    }

    Generified.Action<Void> action1 = new Generified.Action<Void>() {
        @Override
        public void act(Void aVoid) {
            str.substring(0, 1).toUpperCase();
        }
    };

    Generified.Action<Void> action2 = new Generified.Action<Void>() {
        @Override
        public void act(Void aVoid) {
            Character.toUpperCase(str.charAt(0));
        }
    };

    Generified.Action<Void> action3 = new Generified.Action<Void>() {
        @Override
        public void act(Void aVoid) {//English Only
            char ch = str.charAt(0);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 32);
            }
        }
    };
}
