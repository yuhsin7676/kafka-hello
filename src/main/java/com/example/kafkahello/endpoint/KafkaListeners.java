package com.example.kafkahello.endpoint;

import com.example.kafkahello.dto.TTopicDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "groupId")
    void listener(String message) {
        System.out.println("get message: %s".formatted(message));
    }

}
