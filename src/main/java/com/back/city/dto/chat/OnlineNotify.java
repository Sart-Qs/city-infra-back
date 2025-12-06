package com.back.city.dto.chat;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlineNotify {
    Long userId;
    String userName;
}
