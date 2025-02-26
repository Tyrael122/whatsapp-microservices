package org.contoso.messageclient.services.dtos;

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

    public static ChatMessageDTO build(String sender, String chatId, String content) {
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setSender(sender);
        chatMessageDTO.setChatId(chatId);
        chatMessageDTO.setContent(content);
        return chatMessageDTO;
    }
}
