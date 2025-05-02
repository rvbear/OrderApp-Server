package com.kiosk.server.domain.order.data.dto.in;

import com.kiosk.server.domain.order.data.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateOrderDto {
    private UUID userId;
    private int totalPrice;
    private int menuNum;
    private OrderType orderType;
}
