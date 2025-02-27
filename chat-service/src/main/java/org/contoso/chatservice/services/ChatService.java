package org.contoso.chatservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.chatservice.models.Chat;
import org.contoso.chatservice.models.ChatUpdateRequest;
import org.contoso.chatservice.models.ChatUser;
import org.contoso.chatservice.models.dtos.ChatRequest;
import org.contoso.chatservice.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Chat updateChat(UUID chatId, ChatUpdateRequest user) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();

        chat.setUsers(new ArrayList<>());

        user.getUsers().forEach(userId -> {
            ChatUser chatUser = new ChatUser();
            chatUser.setId(UUID.fromString(userId));
            chat.getUsers().add(chatUser);
        });

        return chatRepository.save(chat);
    }

    public List<Chat> getChats() {
        return chatRepository.findAll();
    }
}
