package org.contoso.messagerouterservice.service.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ChatMessageDTO {

    private UUID id;

    private String sender;
    private String chatId;
    private LocalDateTime timestamp;

    private String content;
}
