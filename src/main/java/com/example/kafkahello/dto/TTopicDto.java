package com.example.kafkahello.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TTopicDto(
        UUID id,
        LocalDateTime created,
        String type,
        String message,
        int number
){}
