package org.contoso.chatservice.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {
    private String name;
    private List<String> users;
}
