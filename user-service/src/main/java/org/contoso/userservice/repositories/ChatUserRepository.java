package org.contoso.userservice.repositories;

import org.contoso.userservice.models.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, UUID> {

    List<ChatUser> findAllByName(String username);
}
