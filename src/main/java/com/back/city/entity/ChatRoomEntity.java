package com.back.city.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatId;
    private String chatName;
    private String LastMessage;
    private Long unReadMessages;
    private Long senderId;
    private Long recipientId;
    private String avatar;
}

//TODO переделать под групповой чат
//TODO добавить время последнего сообщения
