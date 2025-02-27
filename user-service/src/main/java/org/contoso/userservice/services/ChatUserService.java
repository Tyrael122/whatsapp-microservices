package org.contoso.userservice.services;

import lombok.RequiredArgsConstructor;
import org.contoso.userservice.models.ChatUser;
import org.contoso.userservice.repositories.ChatUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;

    public ChatUser createUser(ChatUser user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (!getUserByUsername(user.getName()).isEmpty()) {
            throw new IllegalArgumentException("User already exists");
        }

        return chatUserRepository.save(user);
    }

    public List<ChatUser> getUsers() {
        return chatUserRepository.findAll();
    }

    public ChatUser getUser(String userId) {
        return chatUserRepository.findById(UUID.fromString(userId)).orElseThrow();
    }

    public List<ChatUser> getUserByUsername(String username) {
        return chatUserRepository.findAllByName(username);
    }
}
