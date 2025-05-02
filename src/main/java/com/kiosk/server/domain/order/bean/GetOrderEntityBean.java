package com.kiosk.server.domain.order.bean;

import com.kiosk.server.domain.order.data.OrderEntity;
import com.kiosk.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetOrderEntityBean {
    private final OrderRepository orderRepository;

    public int exec() {
        OrderEntity order = orderRepository.findTopByOrderByCodeDesc();
        return order == null ? 1 : order.getCode() + 1;
    }

    public OrderEntity exec(UUID orderId) {
        return orderRepository.findByOrderIdAndHasDelete(orderId, false);
    }

    public List<OrderEntity> exec(boolean hasDelete) {
        return orderRepository.findAllByHasDelete(hasDelete);
    }
}
