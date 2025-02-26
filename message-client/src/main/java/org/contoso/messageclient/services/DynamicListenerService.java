package org.contoso.messageclient.services;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class DynamicListenerService {

    private final ConnectionFactory connectionFactory;

    public DynamicListenerService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void addListener(String queueName) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(message -> {
            String receivedMessage = new String(message.getBody());
            System.out.println("Received in " + queueName + ": " + receivedMessage);
        });

        container.setAutoStartup(true);
        container.start();
    }
}
