package event;

import support.SpringUtil;

/**
 * @author youmoo
 * @since 2014-08-29 下午5:46
 */
public class App {

    public static void main(String[] args) {
        SpringUtil.init("/spring/spring-bean.xml");
        Trade trade = SpringUtil.get(Trade.class);
        trade.setSellerMemo("你妹");
    }
}
