package intro;

import com.lmax.disruptor.EventFactory;

/**
 * @author youmoo
 * @since 2014-10-24 10:17 AM
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
