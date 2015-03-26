package event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author youmoo
 * @since 2014-08-29 下午5:41
 */
@Component
public class Trade implements ApplicationEventPublisherAware {
    ApplicationEventPublisher publisher;

    String seller;
    String sellerMemo;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerMemo() {
        return sellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        this.sellerMemo = sellerMemo;
        publisher.publishEvent(new ModifyEvent(this));
    }
}
