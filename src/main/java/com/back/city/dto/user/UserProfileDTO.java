package com.back.city.dto.user;

import lombok.Builder;
import lombok.Data;

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
}
