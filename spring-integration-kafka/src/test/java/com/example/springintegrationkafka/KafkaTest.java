package com.example.springintegrationkafka;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/integration/kafka-context.xml"})
public class KafkaTest {

    @Autowired
    @Qualifier("inputToKafka")
    MessageChannel messageChannel;

    @Autowired
    @Qualifier("inputFromKafka")
    PollableChannel pollableChannel;


    @Test
    public void sendMsg() throws Exception {

        for (int i = 0; i < 15; i++) {
            Message<String> message = new GenericMessage<String>("test-------------" + (i + 100));
            boolean flag = messageChannel.send(message);

            System.out.println(flag + "=============" + (i + 100));
        }

        Message<?> received = pollableChannel.receive(10000);
        while (received != null) {
            System.out.println("|||" + received);
            received = pollableChannel.receive(10000);
        }

    }

}