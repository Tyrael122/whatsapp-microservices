package org.contoso.messageclient.services;

import lombok.RequiredArgsConstructor;
import org.contoso.messageclient.JsonUtil;
import org.contoso.messageclient.config.RabbitMQConfig;
import org.contoso.messageclient.services.dtos.ChatMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducer {
    private String userIdLoggedIn;

    private final DynamicQueueService dynamicQueueService;
    private final DynamicListenerService dynamicListenerService;

    private final RabbitTemplate rabbitTemplate;

    @Value("${instance.name}")
    private String instanceName;


    public void login(String userId) {
        System.out.println("User " + userId + " logged in");

        userIdLoggedIn = userId;

        String queueName = createQueue(userId);
        dynamicListenerService.addListener(queueName);
    }

    public void sendMessage(String chatId, String message) {
        if (userIdLoggedIn == null) {
            System.out.println("User not logged in");
            return;
        }

        var messageDto = ChatMessageDTO.build(userIdLoggedIn, chatId, message);

        System.out.println("Sending message: " + messageDto);

        rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, JsonUtil.parseObjectToJson(messageDto));
    }

    private String createQueue(String userId) {
        if (QueueNameManager.getQueueName(instanceName) != null) {
            return QueueNameManager.getQueueName(instanceName);
        }

        String queueName = dynamicQueueService.createAndBindQueue(userId);
        QueueNameManager.saveQueueName(instanceName, queueName);

        return queueName;
    }
}