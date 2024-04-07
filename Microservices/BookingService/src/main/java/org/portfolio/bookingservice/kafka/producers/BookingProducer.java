package org.portfolio.bookingservice.kafka.producers;

import lombok.AllArgsConstructor;
import org.portfolio.bookingservice.kafka.events.HomeBookedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingProducer.class);
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public void sendMessage(HomeBookedEvent homeBookedEvent){
        LOGGER.info(String.format("Home booked =>%s",homeBookedEvent.toString()));

        Message<HomeBookedEvent> message = MessageBuilder.withPayload(homeBookedEvent)
                .setHeader(KafkaHeaders.TOPIC,"home-booked")
                .build();

        kafkaTemplate.send(message);
    }
}
