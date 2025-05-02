package com.kiosk.server.domain.user.data.dto.out;

import com.kiosk.server.domain.user.data.UserEntity;
import com.kiosk.server.domain.user.data.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetUserDto {
    private UUID userId;
    private String userName;
    private String email;
    private UserRole role;

    @Builder
    public GetUserDto(UserEntity user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
