package com.kiosk.server.domain.order.data;

import com.kiosk.server.domain.order.data.dto.in.CreateOrderDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "`orders`")
@Entity
public class OrderEntity {
    @Id
    private UUID orderId;
    private OrderState state;
    private OrderType orderType;
    private int totalPrice;
    private int menuNum;
    private int code;
    private LocalDateTime orderTime;
    private UUID userId;
    private boolean hasDelete;

    @Builder
    public OrderEntity(CreateOrderDto createOrderDto, int code) {
        this.orderId = UUID.randomUUID();
        this.state = OrderState.READY;
        this.orderType = createOrderDto.getOrderType();
        this.totalPrice = createOrderDto.getTotalPrice();
        this.menuNum = createOrderDto.getMenuNum();
        this.code = code;
        this.orderTime = LocalDateTime.now();
        this.userId = createOrderDto.getUserId();
    }

    public void update(OrderState state) {
        this.state = state;
    }

    public void delete() {
        this.hasDelete = true;
    }
}
