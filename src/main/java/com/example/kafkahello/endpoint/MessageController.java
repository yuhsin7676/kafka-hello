package com.example.kafkahello.endpoint;

import com.example.kafkahello.dto.TTopicDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.topic}")
    private String topic;
    private int i = 0;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/publish")
    @Operation(summary = "Отправляет сообщение в тему t-topic. Продюсер, который в этом же приложении, прочитает это сообщение и выведет в консоль.")
    protected void publish() throws JsonProcessingException {
        TTopicDto tTopicDto = new TTopicDto(UUID.randomUUID(), LocalDateTime.now(), "1", "hello", i++);
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(tTopicDto));
    }

}
