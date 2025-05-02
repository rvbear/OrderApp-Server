package com.kiosk.server.domain.order.bean;

import com.kiosk.server.domain.order.data.OrderEntity;
import com.kiosk.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveOrderEntityBean {
    private final OrderRepository orderRepository;

    public void exec(OrderEntity order) {
        orderRepository.save(order);
    }
}
