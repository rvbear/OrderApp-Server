package com.kiosk.server.domain.order.data.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteOrderDto {
    private UUID orderId;
}
