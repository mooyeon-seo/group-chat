package com.insufficient.chat.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record MessageDTO(
    String id,
    String content,
    String senderId,
    String groupId,
    LocalDateTime createdAt
) implements Serializable {}