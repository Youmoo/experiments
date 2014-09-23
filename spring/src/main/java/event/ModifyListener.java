package event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @autor youmoo
 * @since 2014-08-29 下午5:44
 */
@Component
public class ModifyListener implements ApplicationListener<ModifyEvent> {

    @Override
    public void onApplicationEvent(ModifyEvent modifyEvent) {
        Trade trade = (Trade) modifyEvent.getSource();
        System.out.println("trade修改。。");
    }
}
