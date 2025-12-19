package com.back.city.mapper;

import com.back.city.dto.chat.ChatNotification;
import com.back.city.entity.ChatMessageEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-17T02:49:32+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 25.0.1 (Amazon.com Inc.)"
)
@Component
public class ChatMapperImpl implements ChatMapper {

    @Override
    public ChatNotification toChatNotification(ChatMessageEntity message) {
        if ( message == null ) {
            return null;
        }

        ChatNotification chatNotification = new ChatNotification();

        return chatNotification;
    }
}
