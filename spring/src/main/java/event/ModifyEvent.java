package event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-08-29 下午5:39
 */
public class ModifyEvent extends ApplicationEvent {

    Date date;

    public ModifyEvent(Object source) {
        super(source);
        this.date = new Date();
    }
}
