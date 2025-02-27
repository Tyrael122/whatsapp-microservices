package org.contoso.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.contoso.userservice.models.ChatUser;
import org.contoso.userservice.services.ChatUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ChatUserController {

    private final ChatUserService chatUserService;

    @PostMapping
    public ChatUser createUser(@RequestBody ChatUser user) {
        return chatUserService.createUser(user);
    }

    @GetMapping
    public List<ChatUser> getUsers() {
        return chatUserService.getUsers();
    }

    @GetMapping("/{userId}")
    public ChatUser getUser(@PathVariable("userId") String userId) {
        return chatUserService.getUser(userId);
    }

    @GetMapping("/search")
    public List<ChatUser> getUserByUsername(@RequestParam("name") String username) {
        return chatUserService.getUserByUsername(username);
    }
}
