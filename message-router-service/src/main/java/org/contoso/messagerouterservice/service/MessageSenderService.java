package org.contoso.messagerouterservice.service;

import lombok.RequiredArgsConstructor;
import org.contoso.messagerouterservice.JsonUtil;
import org.contoso.messagerouterservice.config.RabbitMQConfig;
import org.contoso.messagerouterservice.service.dtos.ChatMessageDTO;
import org.contoso.messagerouterservice.service.dtos.ChatUserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageToUsers(List<ChatUserDTO> users, ChatMessageDTO message) {
        for (ChatUserDTO user : users) {
            sendMessageToUser(user, message);
        }
    }

    private void sendMessageToUser(ChatUserDTO user, ChatMessageDTO message) {
        String routingKey = user.getId(); // e.g., "user.123"

        rabbitTemplate.convertAndSend(RabbitMQConfig.chatMessagesExchange, routingKey, JsonUtil.parseObjectToJson(message));
    }
}
