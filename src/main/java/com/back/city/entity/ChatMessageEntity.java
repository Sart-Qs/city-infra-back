package com.back.city.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String content;
    private Date timeStamp;
    //private ReadStatus readStatus;
    //private MessageStatus status;
}

//TODO Добавить статусы
//TODO Поробовать использовать manyToOne для чата