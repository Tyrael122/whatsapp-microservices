package org.contoso.messagerouterservice.service;

import org.contoso.messagerouterservice.service.dtos.ChatUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "chat-service")
public interface ChatServiceClient {

    @GetMapping("/chats/{chatId}/users")
    List<ChatUserDTO> getUsersInChat(@PathVariable("chatId") String chatId);
}
