package com.back.city.repository;

import com.back.city.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    ChatRoomEntity findChatRoomBySenderIdAndRecipientId(Long senderId, Long recipientId);
    List<ChatRoomEntity> findAllChatBySenderId(Long senderId);
}
