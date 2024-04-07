package org.portfolio.notificationservice.kafka;

import org.portfolio.notificationservice.kafka.events.HomeBookedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Example {
    @KafkaListener(topics = "home-booked",groupId = "group1")
    public void listener(HomeBookedEvent data) {
        System.out.println(data.toString());
    }
}
