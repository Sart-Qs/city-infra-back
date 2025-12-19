package com.back.city.services.chat;

import com.back.city.entity.ChatMemberEntity;
import com.back.city.mapper.ChatMemberMapper;
import com.back.city.repository.ChatMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatMemberService {
    private final ChatMemberRepository chatMemberRepository;
    private final ChatMemberMapper chatMemberMapper;

    @Transactional
    public void addMemberToChat(List<Long> members, Long chatId){
        members.stream().map((mem) -> {
            ChatMemberEntity member = chatMemberMapper.createChatMember(mem, chatId);
            chatMemberRepository.save(member);
            return true;
        });
    }

    @Transactional
    public List<Long> findAllUsersChats(Long userId){
        return chatMemberRepository.findAllUsersChatByUserId(userId);
    }
}
