package org.contoso.messageclient.services;

import org.springframework.amqp.core.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DynamicQueueService {
    private final AmqpAdmin amqpAdmin;
    private final DirectExchange exchange;

    public DynamicQueueService(AmqpAdmin amqpAdmin, DirectExchange exchange) {
        this.amqpAdmin = amqpAdmin;
        this.exchange = exchange;
    }

    public String createAndBindQueue(String routingKey) {
        // Generate a unique queue name
        String queueName = "queue-" + UUID.randomUUID();

        // Create a durable, non-exclusive, non-auto-delete queue
        Queue queue = new Queue(queueName, false, false, false);
        amqpAdmin.declareQueue(queue);

        // Create a binding between the queue and exchange
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
        amqpAdmin.declareBinding(binding);

        System.out.println("Created and bound queue: " + queueName + " to exchange with routing key: " + routingKey);
        return queueName;
    }
}
