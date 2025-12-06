package com.back.city.controller;

import com.back.city.dto.chat.ChatNotification;
import com.back.city.entity.ChatMessageEntity;
import com.back.city.entity.ChatRoomEntity;
import com.back.city.services.ChatMessageService;
import com.back.city.services.ChatRoomService;
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
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageEntity chatMessage){
        ChatMessageEntity savedMsg = chatMessageService.saveMessage(chatMessage);
        messagingTemplate.convertAndSendToUser(String.valueOf(chatMessage.getRecipientId()), "/queue/messages",
                ChatNotification.builder()
                .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build());
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public List<ChatMessageEntity> findChatMessages (@PathVariable("senderId") Long senderId, @PathVariable("recipientId") Long recipientId){
        return chatMessageService.findAllRoomMessages(senderId, recipientId);
    }

    @GetMapping("/getChats/{senderId}")
    public List<ChatRoomEntity> findAllChat(@PathVariable Long senderId){
        System.out.println("senderId"+senderId);
        return chatRoomService.getAllChatRooms(senderId);
    }
}
