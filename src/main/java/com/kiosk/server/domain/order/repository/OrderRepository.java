package com.kiosk.server.domain.order.repository;

import com.kiosk.server.domain.order.data.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    OrderEntity findTopByOrderByCodeDesc();

    OrderEntity findByOrderIdAndHasDelete(UUID orderId, boolean hasDelete);

    List<OrderEntity> findAllByHasDelete(boolean hasDelete);
}
