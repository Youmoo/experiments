package perfomance.converters;

import lombok.Data;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-10-17 9:33 PM
 */
@Data
public class Model {

    public Model() {

    }

    public Model(String serviceNick, Date gmtPerformance, Double payAmount) {
        this.serviceNick = serviceNick;
        this.gmtPerformance = gmtPerformance;
        this.payAmount = payAmount;
    }

    Double payAmount;
    String serviceNick;
    Date gmtPerformance;
}
