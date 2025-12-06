package com.back.city.dto.auth;

import lombok.*;

@Data
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String token;
}
