# WhatsApp Microservices - Proof of Concept

## Overview
This project simulates the backend architecture of WhatsApp using microservices and RabbitMQ for message routing. The goal was to develop a system that can send and receive messages across multiple devices for a single user while maintaining an efficient and scalable messaging flow.

## Architecture
The system consists of multiple microservices:

### 1. **Message Device**
- Runs on the user's device.
- Binds to a queue to send messages to the server.
- Listens to a queue to receive incoming messages.

### 2. **Message Server**
- Receives messages sent from devices.
- Broadcasts messages to all devices bound to the respective queue.

### 3. **Chat Microservice**
- Manages the creation of chat groups.
- Handles the addition of users to chats.

### 4. **User Microservice**
- Manages user registration and authentication.

### 5. **Message Router Microservice**
- Receives messages and determines the recipients.
- Queries the Chat Microservice to identify the users in a chat.
- Caches responses for efficiency.

## Implementation Steps
1. **Develop core microservices**: 
   - Chat management
   - User management
   - Message routing
2. **Deploy Kubernetes and expose the chat service endpoint.**
3. **Develop and integrate the Android app.** ✅
4. **Connect the Android app to the microservices.** ✅
5. **Integrate RabbitMQ to handle messaging.** ✅
6. **Test messaging between multiple devices.** ✅
7. **Implement chat creation within the application.** ✅
8. **Enable adding users to chats at creation.** ✅
9. **Enable adding users to existing chats.** ✅
10. **Prepare the Proof of Concept (PoC) for deployment.** ✅
11. **Integrate with cloud services (Future Scope).**

## Testing & Validation
- ✅ Successfully tested with multiple clients connecting to a single user.
- ✅ Ensured message delivery to appropriate chat members.
- ✅ Verified that messages are not delivered to unintended recipients.
- ✅ Tested reconnection scenarios where lost messages are received after reconnecting.

## Future Improvements
- **User Microservice:** Completed for better user management. ✅
- **Load Testing:** Considered K6 but deemed complex due to dynamic queue creation in RabbitMQ.
- **Cloud Deployment:** The next logical step for scalability.

## [Android App](https://github.com/Tyrael122/whatsapp-microservices-app)
The Android application has been fully developed and includes the following features:
- **Simple Login Screen** (Username-based, automatic user creation if non-existent).
- **Fetching Available Chats** via an API endpoint.
- **Real-time Messaging** with RabbitMQ integration.
- **No Message History Retrieval**, only live messaging.

## Conclusion
The project successfully demonstrates how WhatsApp-like messaging can be implemented using microservices and RabbitMQ. With the Android app fully implemented, the next steps involve cloud deployment and potential optimizations for scalability.
