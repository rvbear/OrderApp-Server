package com.kiosk.server.domain.user.data;

import com.kiosk.server.domain.user.data.dto.in.UpdateUserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "`users`")
@Entity
@Builder
public class UserEntity {
    @Id
    private UUID userId;
    private String email;
    private String passWord;
    private String userName;
    private UserRole role;
    private boolean hasDelete;

    public void update(UpdateUserDto userDto) {
        this.userName = userDto.getUserName();
        this.role = userDto.getRole();
    }

    public void delete() {
        this.hasDelete = true;
    }
}
