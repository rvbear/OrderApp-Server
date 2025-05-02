package com.kiosk.server.domain.menu.service;

import com.kiosk.server.domain.menu.bean.GetMenuEntityBean;
import com.kiosk.server.domain.menu.bean.SaveMenuEntityBean;
import com.kiosk.server.domain.menu.data.MenuEntity;
import com.kiosk.server.domain.menu.data.dto.in.CreateMenuDto;
import com.kiosk.server.domain.menu.data.dto.in.DeleteMenuDto;
import com.kiosk.server.domain.menu.data.dto.in.UpdateMenuDto;
import com.kiosk.server.domain.menu.data.dto.out.GetMenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final SaveMenuEntityBean saveMenuEntityBean;
    private final GetMenuEntityBean getMenuEntityBean;

    public UUID createMenu(CreateMenuDto createMenuDto) {
        MenuEntity menu = MenuEntity.builder().createMenuDto(createMenuDto).build();

        saveMenuEntityBean.exec(menu);

        MenuEntity getMenu = getMenuEntityBean.exec(menu.getMenuId());

        return getMenu == null ? null : getMenu.getMenuId();
    }

    public UUID updateMenu(UpdateMenuDto updateMenuDto) {
        MenuEntity menu = getMenuEntityBean.exec(updateMenuDto.getMenuId());

        if (menu == null) {
            return null;
        }

        menu.update(updateMenuDto);
        saveMenuEntityBean.exec(menu);

        return menu.getMenuId();
    }

    public boolean deleteMenu(DeleteMenuDto deleteMenuDto) {
        MenuEntity menu = getMenuEntityBean.exec(deleteMenuDto.getMenuId());

        if (menu == null) {
            return false;
        }

        menu.delete();
        saveMenuEntityBean.exec(menu);

        return true;
    }

    public GetMenuDto getMenu(UUID menuId) {
        MenuEntity menu = getMenuEntityBean.exec(menuId);

        if (menu == null) {
            return null;
        }

        return GetMenuDto.builder().menu(menu).build();
    }

    public List<GetMenuDto> getMenuAll() {
        List<MenuEntity> menuList = getMenuEntityBean.exec();

        if (menuList.isEmpty()) {
            return Collections.emptyList();
        }

        return menuList.stream()
                .map(menu -> GetMenuDto.builder().menu(menu).build())
                .collect(Collectors.toList());
    }
}
