package com.back.city.mapper;

import com.back.city.dto.chat.ChatNotification;
import com.back.city.dto.message.MessageRequest;
import com.back.city.entity.ChatMessageEntity;
import com.back.city.entity.ChatRoomEntity;
import com.back.city.entity.UserEntity;
import io.swagger.v3.core.util.Json;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMapper {

    ChatNotification toChatNotification(ChatMessageEntity message);

    default ChatRoomEntity createChatRoom (String userName){
        return ChatRoomEntity.builder()
                .id(null)
                .chatName(String.valueOf(userName))
                .LastMessage(null)
                .unReadMessages(null)
                .avatar("d")
                .build();
    }

    default ChatMessageEntity toChatMessageEntity(MessageRequest request){
        return ChatMessageEntity.builder()
                .id(null)
                .chatId(request.getChatId())
                .userId(request.getUserId())
                .content(request.getContent())
                .timeStamp(new Date())
                .build();
    }
}
//TODO поменять для названия чата