package com.kiosk.server.domain.user.bean;

import com.kiosk.server.domain.user.data.UserEntity;
import com.kiosk.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetUserEntityBean {
    private final UserRepository userRepository;

    public UserEntity exec(UUID userId) {
        return userRepository.findByUserIdAndHasDelete(userId, false);
    }

    public UserEntity exec(String email) {
        return userRepository.findByEmailAndHasDelete(email, false);
    }

    public List<UserEntity> exec() {
        return userRepository.findAllByHasDelete(false);
    }
}
