package org.contoso.messageclient.controllers;

import lombok.RequiredArgsConstructor;
import org.contoso.messageclient.services.MessageProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageClientController {

    private final MessageProducer messageProducer;

    @PostMapping("/login")
    public void login(@RequestParam String userId) {
        System.out.println("User " + userId + " logged in");
        messageProducer.login(userId);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam String chatId, @RequestParam String message) {
        messageProducer.sendMessage(chatId, message);
    }
}
