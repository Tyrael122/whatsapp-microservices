package org.contoso.messageclient.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String queueName = "message-sending";
    public static final String chatMessagesExchange = "chat-messages-exchange";

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange chatExchange() {
        return new DirectExchange(chatMessagesExchange);
    }
}
