package org.contoso.messagerouterservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.contoso.messagerouterservice.JsonUtil;
import org.contoso.messagerouterservice.config.RabbitMQConfig;
import org.contoso.messagerouterservice.service.dtos.ChatMessageDTO;
import org.contoso.messagerouterservice.service.MessageRouterService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final MessageRouterService messageRouterService;

    @RabbitListener(queues = RabbitMQConfig.queueName)
    public void receiveMessage(Message message) throws JsonProcessingException {
        System.out.println("Received message: " + message);
        System.out.println("Body: " + new String(message.getBody()));

        var messageDto = JsonUtil.parseJsonToObject(new String(message.getBody()), ChatMessageDTO.class);
        messageRouterService.routeMessage(messageDto);
    }
}
