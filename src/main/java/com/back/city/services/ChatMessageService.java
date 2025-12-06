package com.back.city.services;

import com.back.city.entity.ChatMessageEntity;
import com.back.city.entity.ChatRoomEntity;
import com.back.city.repository.ChatMessageRepository;
import com.back.city.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessageEntity saveMessage(ChatMessageEntity chatMessage){
        ChatRoomEntity chatRoom = chatRoomService.getChatRoom(chatMessage.getSenderId(), chatMessage.getRecipientId());
        chatRoomService.saveLastMessage(chatMessage.getSenderId(), chatMessage.getRecipientId(), chatMessage.getContent());

        chatMessage.setChatId(chatRoom.getChatId());
        chatMessage.setTimeStamp(new Date());
        chatMessageRepository.save(chatMessage);
        chatRoom.setLastMessage(chatMessage.getContent());
        return chatMessage;
    }

    public List<ChatMessageEntity> findAllRoomMessages(Long senderId, Long recipientId){
        ChatRoomEntity chatRoom = chatRoomService.getChatRoom(senderId, recipientId);
        return chatMessageRepository.findByChatId(chatRoom.getChatId());
    }
}
