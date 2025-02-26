package org.contoso.chatservice.repositories;

import org.contoso.chatservice.models.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ChatUser, UUID> {

}
