package com.back.city.dto.event;

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
    private Long userId;
    private String description;
    private String avatar;
}
