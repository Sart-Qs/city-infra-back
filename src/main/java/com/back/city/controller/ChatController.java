package com.back.city.controller;

import com.back.city.dto.message.MessageRequest;
import com.back.city.dto.status.StatusRequest;
import com.back.city.entity.ChatMessageEntity;
import com.back.city.entity.ChatRoomEntity;
import com.back.city.entity.UserEntity;
import com.back.city.mapper.ChatMapper;
import com.back.city.repository.UserRepository;
import com.back.city.services.chat.ChatMessageService;
import com.back.city.services.chat.ChatRoomService;
import com.back.city.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chats")
@AllArgsConstructor
public class ChatController {
    //TODO Перенести в сервисы
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final ChatMapper chatMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    //TODO Пменять на DTO
    @MessageMapping("/chat")
    public void processMessage(@Payload MessageRequest chatMessage){
        ChatMessageEntity savedMsg = chatMessageService.saveMessage(chatMessage);
        messagingTemplate.convertAndSendToUser(String.valueOf(chatMessage.getRecipientId()), "/queue/messages",
                chatMapper.toChatNotification(savedMsg));
    }
    //TODO Сделать чтобы у пользователя был свой пул юзеров чтобы получать статусы не всех пользователей
    @MessageMapping("/status")
    public void processStatus(@Payload StatusRequest status){
        UserEntity user = userRepository.findUserById(status.getUserId());
        user.setStatus(status.getStatus());
        userRepository.save(user);
        messagingTemplate.convertAndSend("/topic/online", user);
    }

    @GetMapping("/messages/{chatId}")
    public List<ChatMessageEntity> findChatMessages (@PathVariable Long chatId){
        return chatMessageService.findAllRoomMessages(chatId);
    }

    @GetMapping("/getChats/{userId}")
    public List<ChatRoomEntity> findAllChat(@PathVariable Long userId){
        return chatRoomService.getAllChatRooms(userId);
    }
}
