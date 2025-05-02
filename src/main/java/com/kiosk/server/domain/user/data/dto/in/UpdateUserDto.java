package com.kiosk.server.domain.user.data.dto.in;

import com.kiosk.server.domain.user.data.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserDto {
    private UUID userId;
    private String userName;
    private UserRole role;
}
