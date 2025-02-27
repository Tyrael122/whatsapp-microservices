// chat-service.js
import { RabbitMQService } from './rabbitmq-service.js';

// Create an instance of RabbitMQService
const rabbitMQService = new RabbitMQService();
rabbitMQService.connect("message-sending", () => {})

// Simulate retrieving a list of chats
export function retrieveListOfChats() {
    return [
        { id: 'chat1', name: 'Chat 1' },
        { id: 'chat2', name: 'Chat 2' },
    ];
}

// Simulate sending a message to the server
export function sendMessageToServer(chatId, message) {
    console.log(`Message sent to server for chat ${chatId}: ${message}`);

    // Use RabbitMQService to send the message
    rabbitMQService.sendMessage(chatId, message);
}

// Simulate requesting a chat connection and setting up a listener
export function requestChatConnection(chatId, onMessageReceived) {
    console.log(`Requesting connection to chat: ${chatId}`);

    // Simulate receiving a message from the server after a delay
    setTimeout(() => {
        const newMessage = {
            sender: 'Server',
            message: 'This is a test message from the server.',
        };
        onMessageReceived(newMessage); // Trigger the listener
    }, 3000); // Simulate a 3-second delay
}