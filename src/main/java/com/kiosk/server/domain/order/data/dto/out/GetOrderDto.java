package com.kiosk.server.domain.order.data.dto.out;

import com.kiosk.server.domain.order.data.OrderEntity;
import com.kiosk.server.domain.order.data.OrderState;
import com.kiosk.server.domain.order.data.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetOrderDto {
    private UUID orderId;
    private OrderState state;
    private OrderType orderType;
    private int totalPrice;
    private int menuNum;
    private int code;
    private LocalDateTime orderTime;
    private UUID userId;

    @Builder
    public GetOrderDto(OrderEntity order) {
        this.orderId = order.getOrderId();
        this.state = order.getState();
        this.orderType = order.getOrderType();
        this.totalPrice = order.getTotalPrice();
        this.menuNum = order.getMenuNum();
        this.code = order.getCode();
        this.orderTime = order.getOrderTime();
        this.userId = order.getUserId();
    }
}
