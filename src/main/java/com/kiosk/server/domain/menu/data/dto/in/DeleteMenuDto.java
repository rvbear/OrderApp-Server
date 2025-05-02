package com.kiosk.server.domain.menu.data.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMenuDto {
    private UUID menuId;
}
