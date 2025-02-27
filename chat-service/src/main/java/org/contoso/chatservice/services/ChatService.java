package org.contoso.chatservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.chatservice.models.Chat;
import org.contoso.chatservice.models.ChatUser;
import org.contoso.chatservice.models.dtos.ChatRequest;
import org.contoso.chatservice.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat createChat(ChatRequest chatRequest) {
        Chat chat = new Chat();
        chat.setName(chatRequest.getName());

        chatRequest.getUsers().forEach(user -> {
            ChatUser chatUser = new ChatUser();
            chatUser.setId(UUID.fromString(user));
            chat.getUsers().add(chatUser);
        });

        return chatRepository.save(chat);
    }

    public List<ChatUser> getChatUsers(UUID chatId) {
        return chatRepository.findById(chatId).orElseThrow().getUsers();
    }

    public Chat addUserToChat(UUID uuid, ChatUser user) {
        Chat chat = chatRepository.findById(uuid).orElseThrow();
        chat.getUsers().add(user);

        return chatRepository.save(chat);
    }

    public List<Chat> getChats() {
        return chatRepository.findAll();
    }
}
