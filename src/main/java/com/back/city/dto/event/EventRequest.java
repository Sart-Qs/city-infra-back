package com.back.city.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class EventRequest {
    private Long id;
    private Float[] coordinates;
    private Long userId;
    private String description;

}
