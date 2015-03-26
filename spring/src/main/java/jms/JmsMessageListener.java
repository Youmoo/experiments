package jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消息事件接收
 *
 * @author youmoo
 * @since 2015-03-26 10:34 AM
 */
@Component
public class JmsMessageListener implements SessionAwareMessageListener<TextMessage> {

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        // This is the received message
        System.out.println("Receive: " + message.getText());

        // Let's prepare a reply message - a "ACK" String
        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText("ACK");

        // Message send back to the replyTo address of the income message.
        // Like replying an email somehow.
        MessageProducer producer = session.createProducer(message.getJMSReplyTo());
        producer.send(new ActiveMQQueue("testQueue"), textMessage);
    }
}
