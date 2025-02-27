// rabbitmq-service.js
// import * as StompJs from 'https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js';

export class RabbitMQService {
    constructor() {
        this.client = null;
    }

    /**
     * Connects to RabbitMQ using STOMP over WebSockets.
     * @param {string} queueName - The name of the queue to connect to.
     * @param {function} onMessageCallback - Callback function to handle incoming messages.
     * @returns {Promise<void>}
     */
    async connect(queueName, onMessageCallback) {
        // RabbitMQ WebSocket URL (replace with your RabbitMQ server URL)
        const websocketUrl = 'ws://localhost:39919/ws'; // Default RabbitMQ WebSocket port
        const username = 'guest'; // Default RabbitMQ username
        const password = 'guest'; // Default RabbitMQ password

        // Create a STOMP client
        this.client = new StompJs.Client({
            brokerURL: websocketUrl,
            connectHeaders: {
                login: username,
                passcode: password,
            },
            debug: function (str) {
                console.log('STOMP: ' + str);
            },
            reconnectDelay: 5000, // Reconnect after 5 seconds if disconnected
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
        });

        // Handle connection
        this.client.onConnect = (frame) => {
            console.log('Connected to RabbitMQ via STOMP');

            // Subscribe to the queue
            this.client.subscribe(`/queue/${queueName}`, (message) => {
                if (onMessageCallback) {
                    onMessageCallback(message.body);
                }
            });

            console.log(`Subscribed to queue "${queueName}"`);
        };

        // Handle errors
        this.client.onStompError = (frame) => {
            console.error('STOMP error:', frame.headers.message);
        };

        // Activate the client
        this.client.activate();
    }

    /**
     * Sends a message to the specified queue.
     * @param {string} queueName - The name of the queue to send the message to.
     * @param {string} message - The message to send.
     */
    sendMessage(queueName, message) {
        if (!this.client || !this.client.connected) {
            console.error('Client is not connected to RabbitMQ.');
            return;
        }

        this.client.publish({
            destination: `/queue/${queueName}`,
            body: message,
        });

        console.log(`Message sent to queue "${queueName}":`, message);
    }

    /**
     * Disconnects from RabbitMQ.
     */
    disconnect() {
        if (this.client) {
            this.client.deactivate();
            console.log('Disconnected from RabbitMQ.');
        }
    }
}