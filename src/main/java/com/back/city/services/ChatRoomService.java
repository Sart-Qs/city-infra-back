package com.back.city.services;

import com.back.city.entity.ChatRoomEntity;
import com.back.city.entity.ProfileEntity;
import com.back.city.entity.UserEntity;
import com.back.city.repository.ChatRoomRepository;
import com.back.city.repository.ProfileRepository;
import com.back.city.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatRoomService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomEntity getChatRoom(Long senderId, Long recipientId){
        //TODO Добавить аватарки для группового чата
        ChatRoomEntity room = chatRoomRepository.findChatRoomBySenderIdAndRecipientId(senderId, recipientId);
        System.out.println("room: " + room);
        if (room == null){
            Optional<UserEntity> sender = userRepository.findById(senderId);
            Optional<UserEntity> recipient = userRepository.findById(recipientId);

            ChatRoomEntity newRoom = ChatRoomEntity
                    .builder()
                    .id(null)
                    .chatId(String.valueOf(senderId)+"_"+String.valueOf(recipientId))
                    .chatName(String.valueOf(sender.get().getFirstName()) +" "+ String.valueOf(sender.get().getLastName()))
                    .LastMessage(null)
                    .unReadMessages(null)
                    .avatar(sender.get().getProfileEntity().getUserAvatar())
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            ChatRoomEntity newRoomRecipient = ChatRoomEntity
                    .builder()
                    .id(null)
                    .chatId(String.valueOf(senderId)+"_"+String.valueOf(recipientId))
                    .chatName((String.valueOf(recipient.get().getFirstName()) +" "+ String.valueOf(recipient.get().getLastName())))
                    .LastMessage(null)
                    .unReadMessages(null)
                    .avatar(recipient.get().getProfileEntity().getUserAvatar())
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();
            chatRoomRepository.save(newRoomRecipient);
            return chatRoomRepository.save(newRoom);
        }
        return room;
    }

    public List<ChatRoomEntity> getAllChatRooms (Long senderId){
        return chatRoomRepository.findAllChatBySenderId(senderId);
    }

    public ChatRoomEntity saveLastMessage(Long senderId, Long recipientId, String lastMessage){
        ChatRoomEntity senderRoom = chatRoomRepository.findChatRoomBySenderIdAndRecipientId(senderId, recipientId);
        ChatRoomEntity recipientRoom = chatRoomRepository.findChatRoomBySenderIdAndRecipientId(recipientId, senderId);
        senderRoom.setLastMessage(lastMessage);
        recipientRoom.setLastMessage(lastMessage);
        chatRoomRepository.save(recipientRoom);
        return chatRoomRepository.save(senderRoom);

    }
}
