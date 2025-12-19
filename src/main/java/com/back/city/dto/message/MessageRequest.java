package com.back.city.dto.message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MessageRequest {
    private Long userId;
    private Long recipientId;
    private Long chatId;
    private String content;
}
