package intro;

import com.lmax.disruptor.EventHandler;

/**
 * @author youmoo
 * @since 2014-10-24 10:19 AM
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event);
    }
}
