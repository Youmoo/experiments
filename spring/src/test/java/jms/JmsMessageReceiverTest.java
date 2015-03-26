package jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Queue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-jms.xml"})
public class JmsMessageReceiverTest {

    @Autowired
    JmsMessageReceiver jmsMessageReceiver;

    Queue testQueue;

    @Before
    public void setUp() {
        this.testQueue = new ActiveMQQueue("testQueue");
    }

    @After
    public void tearDown() {
        this.jmsMessageReceiver = null;
        this.testQueue = null;

    }

    @Test
    @Ignore
    public void receiveDefaultTest() {
        this.jmsMessageReceiver.receive();
    }

    @Test
    @Ignore
    public void receiveCustomDestinationTest() {
        this.jmsMessageReceiver.receive(this.testQueue);
    }

    @Test
    @Ignore
    public void receiveCustomDestinationName() {
        this.jmsMessageReceiver.receive("testQueue");
    }
}
