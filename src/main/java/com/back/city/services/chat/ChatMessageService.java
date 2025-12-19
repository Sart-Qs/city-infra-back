package com.back.city.services.chat;

import com.back.city.dto.message.MessageRequest;
import com.back.city.entity.ChatMessageEntity;
import com.back.city.entity.ChatRoomEntity;
import com.back.city.mapper.ChatMapper;
import com.back.city.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final ChatMapper chatMapper;
    //TODO добавить дто вместо ентити
    @Transactional
    public ChatMessageEntity saveMessage(MessageRequest chatMessage){
        ChatRoomEntity chatRoom = chatRoomService.getChatRoom(chatMessage);
        if (chatMessage.getChatId() == null){
            chatMessage.setChatId(chatRoom.getId());
        }
        chatRoomService.saveLastMessage(chatMessage.getChatId(), chatMessage.getContent());
        chatMessage.setChatId(chatRoom.getId());
        ChatMessageEntity saveMsg = chatMapper.toChatMessageEntity(chatMessage);
        chatMessageRepository.save(saveMsg);
        chatRoom.setLastMessage(chatMessage.getContent());
        return saveMsg;
    }

    @Transactional
    public List<ChatMessageEntity> findAllRoomMessages(Long chatId){
        return chatMessageRepository.findByChatId(chatId);
    }
}
