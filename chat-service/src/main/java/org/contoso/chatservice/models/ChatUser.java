package org.contoso.chatservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
