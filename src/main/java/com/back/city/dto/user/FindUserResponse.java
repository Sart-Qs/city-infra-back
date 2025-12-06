package com.back.city.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class FindUserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String avatar;
}
