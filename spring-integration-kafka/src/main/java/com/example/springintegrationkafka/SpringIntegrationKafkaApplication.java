package com.example.springintegrationkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;

@SpringBootApplication
public class SpringIntegrationKafkaApplication {

    public static void main(String[] args) {
        ApplicationContext context =new ClassPathXmlApplicationContext("classpath*:/spring/integration/integration-kafka-consumer.xml");
        QueueChannel queueChannel=(QueueChannel) context.getBean("stopKafka");
        Message msg=null;
        while((msg=queueChannel.receive(-1))!=null){
            String map=(String) msg.getPayload();
            System.out.println(map);
        }


    }

}
