package org.contoso.chatservice.models;

import lombok.Data;

import java.util.List;

@Data
public class ChatUpdateRequest {
    private List<String> users;
}
