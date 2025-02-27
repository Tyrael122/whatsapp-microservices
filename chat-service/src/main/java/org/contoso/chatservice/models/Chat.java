package org.contoso.chatservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany
    private List<ChatUser> users = new ArrayList<>();

//    @ElementCollection
//    @CollectionTable(name = "chat_users", joinColumns = @JoinColumn(name = "chat_id"))
//    @Column(name = "user_id")
//    private List<UUID> userIds;
}
