package com.kiosk.server.domain.menu.bean;

import com.kiosk.server.domain.menu.data.MenuEntity;
import com.kiosk.server.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetMenuEntityBean {
    private final MenuRepository menuRepository;

    public MenuEntity exec(UUID menuId) {
        return menuRepository.findByMenuIdAndHasDelete(menuId, false);
    }

    public List<MenuEntity> exec() {
        return menuRepository.findAllByHasDelete(false);
    }
}
