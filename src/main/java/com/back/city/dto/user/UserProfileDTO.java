package com.back.city.dto.user;

import com.back.city.entity.EventEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String aboutSelf;
    private String avatar;
    private List<EventEntity> events;
}
