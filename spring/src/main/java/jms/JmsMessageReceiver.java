package jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Message;

/**
 * 消息接收
 */
@Service
public class JmsMessageReceiver {

    @Autowired
    private JmsTemplate jmsTemplate;


    /**
     * Receive message from default
     * destination
     *
     * @return
     */
    public Message receive() {
        return this.jmsTemplate.receive();
    }

    /**
     * Receive message from specified
     * destination
     *
     * @param destination
     * @return
     */
    public Message receive(final Destination destination) {
        return this.jmsTemplate.receive(destination);
    }

    /**
     * Recieve message from specified
     * destination name
     *
     * @param destinationName
     * @return
     */
    public Message receive(final String destinationName) {
        return this.jmsTemplate.receive(destinationName);
    }

}
