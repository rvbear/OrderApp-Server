package com.kiosk.server.domain.menu.data.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuDto {
    private String name;
    private String category;
    private int price;
    private String img;
}
