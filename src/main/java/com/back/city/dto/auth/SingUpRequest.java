package com.back.city.dto.auth;

import lombok.Data;

@Data
public class SingUpRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
