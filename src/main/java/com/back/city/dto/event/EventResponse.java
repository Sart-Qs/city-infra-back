package com.back.city.dto.event;

import com.back.city.dto.user.UserDTO;
import com.back.city.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class EventResponse {
    private Long id;
    private Float[] coordinates;
    private UserDTO user;
    private String description;
    private List<String> fileUrl;
    private Date timestomp;
    private String avatar;
}
