package com.kiosk.server.domain.menu.data;

import com.kiosk.server.domain.menu.data.dto.in.CreateMenuDto;
import com.kiosk.server.domain.menu.data.dto.in.UpdateMenuDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "`menus`")
@Entity
public class MenuEntity {
    @Id
    private UUID menuId;
    private String name;
    private String category;
    private int price;
    private String img;
    private boolean hasDelete;

    @Builder
    public MenuEntity(CreateMenuDto createMenuDto) {
        this.menuId = UUID.randomUUID();
        this.name = createMenuDto.getName();
        this.category = createMenuDto.getCategory();
        this.price = createMenuDto.getPrice();
        this.img = createMenuDto.getImg();
        this.hasDelete = false;
    }

    public void update(UpdateMenuDto updateMenuDto) {
        this.name = updateMenuDto.getName();
        this.category = updateMenuDto.getCategory();
        this.price = updateMenuDto.getPrice();
        this.img = updateMenuDto.getImg();
    }

    public void delete() {
        this.hasDelete = true;
    }
}
