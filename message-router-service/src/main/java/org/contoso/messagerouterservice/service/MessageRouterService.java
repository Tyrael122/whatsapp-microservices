package org.contoso.messagerouterservice.service;

import lombok.RequiredArgsConstructor;
import org.contoso.messagerouterservice.service.dtos.ChatMessageDTO;
import org.contoso.messagerouterservice.service.dtos.ChatUserDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageRouterService {

    private final ChatServiceClient chatServiceClient;
    private final MessageSenderService messageSenderService;

    public void routeMessage(ChatMessageDTO message) {
        fillInAditionalFields(message);

        System.out.println("Routing message: " + message);

        List<ChatUserDTO> users = chatServiceClient.getUsersInChat(message.getChatId());
        messageSenderService.sendMessageToUsers(users, message);
    }

    private static void fillInAditionalFields(ChatMessageDTO message) {
        message.setId(UUID.randomUUID());
        message.setTimestamp(LocalDateTime.now());
    }
}
