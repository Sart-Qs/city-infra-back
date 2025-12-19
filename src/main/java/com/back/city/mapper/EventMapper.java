package com.back.city.mapper;

import com.back.city.dto.event.EventRequest;
import com.back.city.dto.event.EventResponse;
import com.back.city.dto.user.UserDTO;
import com.back.city.entity.EventEntity;
import com.back.city.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {
    //TODO поменять после того как подумаю где хранить аватарки
    default EventResponse toEventResponse(EventEntity event, UserDTO user){
        return EventResponse.builder()
                .id(event.getId())
                .fileUrl(event.getFilesUrl())
                .coordinates(event.getCoordinates())
                .timestomp(event.getTimestomp())
                .description(event.getDescription())
                .user(user)
                .build();
    }


    default EventEntity toEventEntity(EventRequest event, List<String> files){
        //todo rename eventDate
        return EventEntity.builder()
                .id(event.getId())
                .userId(event.getUserId())
                .coordinates(event.getCoordinates())
                .description(event.getDescription())
                .timestomp(new Date())
                .filesUrl(files)
                .build();
    }
}
