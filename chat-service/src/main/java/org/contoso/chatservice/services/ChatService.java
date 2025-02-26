package org.contoso.chatservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.chatservice.models.Chat;
import org.contoso.chatservice.models.ChatUser;
import org.contoso.chatservice.repositories.ChatRepository;
import org.contoso.chatservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Chat createChat() {
        Chat chat = new Chat();
        return chatRepository.save(chat);
    }

    public List<ChatUser> getChatUsers(UUID chatId) {
        return chatRepository.findById(chatId).orElseThrow().getUsers();
    }

    public Chat addUserToChat(UUID uuid, ChatUser user) {
        ChatUser chatUser;

        Optional<ChatUser> optional = userRepository.findById(user.getId());
        if (optional.isEmpty()) {
            chatUser = new ChatUser();

            chatUser.setName(user.getName());
            userRepository.save(chatUser);
        } else {
            chatUser = optional.get();
        }

        Chat chat = chatRepository.findById(uuid).orElseThrow();
        chat.getUsers().add(chatUser);

        return chatRepository.save(chat);
    }
}
