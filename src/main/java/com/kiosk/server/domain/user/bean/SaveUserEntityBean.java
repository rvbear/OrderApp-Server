package com.kiosk.server.domain.user.bean;

import com.kiosk.server.domain.user.data.UserEntity;
import com.kiosk.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveUserEntityBean {
    private final UserRepository userRepository;

    public void exec(UserEntity user) {
        userRepository.save(user);
    }
}
