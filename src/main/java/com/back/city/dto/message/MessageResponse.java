package com.back.city.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private Long id;
    private Long chatId;
    private String content;
    private Long senderId;
    private Long recipientId;
}
