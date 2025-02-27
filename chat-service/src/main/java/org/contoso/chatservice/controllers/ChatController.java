package org.contoso.chatservice.controllers;

import lombok.RequiredArgsConstructor;
import org.contoso.chatservice.models.Chat;
import org.contoso.chatservice.models.ChatUser;
import org.contoso.chatservice.models.dtos.ChatRequest;
import org.contoso.chatservice.services.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public Chat createChat(@RequestBody ChatRequest chatRequest) {
        return chatService.createChat(chatRequest);
    }

    @GetMapping()
    public List<Chat> getChats() {
        return chatService.getChats();
    }

    @PostMapping("/{chatId}/users")
    public Chat addUserToChat(@PathVariable("chatId") String chatId, @RequestBody ChatUser user) {
        return chatService.addUserToChat(UUID.fromString(chatId), user);
    }

    @GetMapping("/{chatId}/users")
    public List<ChatUser> getUsersInChat(@PathVariable("chatId") String chatId) {
        return chatService.getChatUsers(UUID.fromString(chatId));
    }
}
