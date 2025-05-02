package com.kiosk.server.domain.menu.bean;

import com.kiosk.server.domain.menu.data.MenuEntity;
import com.kiosk.server.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveMenuEntityBean {
    private final MenuRepository menuRepository;

    public void exec(MenuEntity menu) {
        menuRepository.save(menu);
    }
}
