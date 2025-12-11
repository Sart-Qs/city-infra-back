package com.back.city.mapper;

import com.back.city.dto.event.EventResponse;
import com.back.city.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {

    default EventResponse toEventResponse(EventEntity entity){
        return EventResponse.builder()
                .build();
    }
}
