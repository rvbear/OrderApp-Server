package com.kiosk.server.domain.order.service;

import com.kiosk.server.domain.order.bean.GetOrderEntityBean;
import com.kiosk.server.domain.order.bean.SaveOrderEntityBean;
import com.kiosk.server.domain.order.data.OrderEntity;
import com.kiosk.server.domain.order.data.dto.in.CreateOrderDto;
import com.kiosk.server.domain.order.data.dto.in.DeleteOrderDto;
import com.kiosk.server.domain.order.data.dto.in.UpdateOrderDto;
import com.kiosk.server.domain.order.data.dto.out.GetOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final GetOrderEntityBean getOrderEntityBean;
    private final SaveOrderEntityBean saveOrderEntityBean;

    public UUID createOrder(CreateOrderDto createOrderDto) {
        int code = getOrderEntityBean.exec();

        OrderEntity order = OrderEntity.builder().createOrderDto(createOrderDto).code(code).build();
        saveOrderEntityBean.exec(order);

        OrderEntity getOrder = getOrderEntityBean.exec(order.getOrderId());
        return getOrder == null ? null : getOrder.getOrderId();
    }

    public UUID updateOrder(UpdateOrderDto updateOrderDto) {
        OrderEntity order = getOrderEntityBean.exec(updateOrderDto.getOrderId());
        if (order == null) {
            return null;
        }

        order.update(updateOrderDto.getState());
        saveOrderEntityBean.exec(order);

        return order.getOrderId();
    }

    public boolean deleteOrder(DeleteOrderDto deleteOrderDto) {
        OrderEntity order = getOrderEntityBean.exec(deleteOrderDto.getOrderId());
        if (order == null) {
            return false;
        }

        order.delete();
        saveOrderEntityBean.exec(order);

        return true;
    }

    public GetOrderDto getOrder(UUID orderId) {
        OrderEntity order = getOrderEntityBean.exec(orderId);
        if (order == null) {
            return null;
        }

        return GetOrderDto.builder().order(order).build();
    }

    public int getOrderCode(UUID orderId) {
        OrderEntity order = getOrderEntityBean.exec(orderId);
        if (order == null) {
            return -1;
        }

        return order.getCode();
    }

    public List<GetOrderDto> getOrderAll() {
        List<OrderEntity> orderList = getOrderEntityBean.exec(false);
        if (orderList.isEmpty()) {
            return Collections.emptyList();
        }

        return orderList.stream()
                .map(order -> GetOrderDto.builder().order(order).build())
                .collect(Collectors.toList());
    }
}
