package com.back.city.dto.event;

import com.back.city.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Builder
@Getter
@Setter
public class EventResponse {
    private Long id;
    private Float[] coordinates;
    private UserEntity user;
    private String description;
    private String avatar;
}
