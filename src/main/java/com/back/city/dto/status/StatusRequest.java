package com.back.city.dto.status;

import com.back.city.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequest {
    private UserStatus status;
    private Long userId;
}
