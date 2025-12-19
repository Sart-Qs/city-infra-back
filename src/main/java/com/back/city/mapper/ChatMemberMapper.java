package com.back.city.mapper;

import com.back.city.entity.ChatMemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMemberMapper {

    default ChatMemberEntity createChatMember(Long userId, Long chatId){
        return ChatMemberEntity.builder()
                .id(null)
                .userId(userId)
                .chatId(chatId)
                .build();
    }
}
