// Import functions from chat-service.js
import { retrieveListOfChats, sendMessageToServer, requestChatConnection } from './chat-service.js';

// Get the username from the query parameter
const urlParams = new URLSearchParams(window.location.search);
const username = urlParams.get('username');
document.getElementById('usernameDisplay').textContent = username;

// Populate the chat list in the sidebar
function populateChatList() {
  const chatList = document.getElementById('chatList');
  const chats = retrieveListOfChats();

  // Clear existing chat list items
  chatList.innerHTML = '';

  // Add each chat to the list
  chats.forEach(chat => {
    const listItem = document.createElement('li');
    const chatLink = document.createElement('a');
    chatLink.href = '#';
    chatLink.setAttribute('data-chat-id', chat.id);
    chatLink.textContent = chat.name;
    listItem.appendChild(chatLink);
    chatList.appendChild(listItem);
  });
}

// Add a message to the chat screen
function addMessageToScreen(chatId, sender, message) {
  const messagesDiv = document.getElementById('messages');
  messagesDiv.innerHTML += `<div class="message"><strong>${sender}:</strong> ${message}</div>`;
  messagesDiv.scrollTop = messagesDiv.scrollHeight; // Auto-scroll to the latest message
}

// Handle chat selection
document.getElementById('chatList').addEventListener('click', function (event) {
  event.preventDefault();
  const chatId = event.target.getAttribute('data-chat-id');
  if (chatId) {
    connectToChat(chatId);
  }
});

// Handle message submission
document.getElementById('messageForm').addEventListener('submit', function (event) {
  event.preventDefault();
  const chatId = 'chat1'; // Replace with dynamic chat ID if needed
  const message = document.getElementById('messageInput').value;
  if (message) {
    sendMessage(chatId, message);
    document.getElementById('messageInput').value = ''; // Clear input
  }
});

// Simulate connecting to a chat
function connectToChat(chatId) {
  console.log(`Connecting to chat: ${chatId}`);
  const messagesDiv = document.getElementById('messages');
  messagesDiv.innerHTML = `<div class="message"><em>Connected to ${chatId}.</em></div>`;

  // Simulate requesting a chat connection and set up a listener for new messages
  requestChatConnection(chatId, (newMessage) => {
    // When a new message is received, add it to the screen
    addMessageToScreen(chatId, newMessage.sender, newMessage.message);
  });
}

// Simulate sending a message
function sendMessage(chatId, message) {
  console.log(`Sending message to ${chatId}: ${message}`);
  addMessageToScreen(chatId, username, message);

  // Simulate sending a message to the server
  sendMessageToServer(chatId, message);
}

// Initialize the chat list when the page loads
populateChatList();