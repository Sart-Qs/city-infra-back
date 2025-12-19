package com.back.city.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "chat_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chatName;
    private String LastMessage;
    private Long unReadMessages;
    private String avatar;
}

//TODO переделать под групповой чат
//TODO добавить время последнего сообщения
//TODO убрать unReadMessage и вместо этого считать по статусу сообщейний
