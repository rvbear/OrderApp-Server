package com.kiosk.server.domain.order.data.dto.in;

import com.kiosk.server.domain.order.data.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateOrderDto {
    private UUID orderId;
    private OrderState state;
}
