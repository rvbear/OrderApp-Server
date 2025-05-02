package com.kiosk.server.domain.menu.data.dto.out;

import com.kiosk.server.domain.menu.data.MenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuDto {
    private UUID menuId;
    private String name;
    private String category;
    private int price;
    private String img;

    @Builder
    public GetMenuDto(MenuEntity menu) {
        this.menuId = menu.getMenuId();
        this.name = menu.getName();
        this.category = menu.getCategory();
        this.price = menu.getPrice();
        this.img = menu.getImg();
    }
}
