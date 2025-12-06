package com.back.city.dto.auth;

import lombok.Data;

@Data
public class SingInRequest {

    private String userName;
    private String password;
}
