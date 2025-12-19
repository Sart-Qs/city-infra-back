package com.back.city.repository;

import com.back.city.entity.ChatMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMemberEntity, Long> {
    List<Long> findAllUsersChatByUserId(Long userId);
}
