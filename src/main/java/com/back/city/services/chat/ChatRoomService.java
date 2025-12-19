package com.back.city.services.chat;

import com.back.city.dto.message.MessageRequest;
import com.back.city.entity.ChatRoomEntity;
import com.back.city.entity.UserEntity;
import com.back.city.mapper.ChatMapper;
import com.back.city.repository.ChatMemberRepository;
import com.back.city.repository.ChatRoomRepository;
import com.back.city.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatRoomService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberService chatMemberService;
    private final ChatMapper chatMapper;

    @Transactional
    public ChatRoomEntity getChatRoom(MessageRequest message){
        //TODO Добавить аватарки для группового чата
        UserEntity user = userRepository.findUserById(message.getUserId());
        ChatRoomEntity room = chatRoomRepository.findChatRoomById(message.getChatId());
        if (room == null){

            ChatRoomEntity newRoom = chatMapper.createChatRoom(user.getFirstName());

            return chatRoomRepository.save(newRoom);
        }
        return room;
    }

    @Transactional
    public List<ChatRoomEntity> getAllChatRooms (Long userId){
        List<Long> chatIds = chatMemberService.findAllUsersChats(userId);
        return chatRoomRepository.findAllById(chatIds);
    }

    @Transactional
    public ChatRoomEntity saveLastMessage(Long chatId, String lastMessage){
        ChatRoomEntity chatRoom = chatRoomRepository.findChatRoomById(chatId);
        chatRoom.setLastMessage(lastMessage);
        return chatRoomRepository.save(chatRoom);

    }
}
