package com.kiosk.server.domain.order.controller;

import com.kiosk.server.domain.order.data.dto.in.CreateOrderDto;
import com.kiosk.server.domain.order.data.dto.in.DeleteOrderDto;
import com.kiosk.server.domain.order.data.dto.in.UpdateOrderDto;
import com.kiosk.server.domain.order.data.dto.out.GetOrderDto;
import com.kiosk.server.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        UUID orderId = orderService.createOrder(createOrderDto);
        boolean success = orderId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "주문 추가 성공" : "주문 추가 실패");
        response.put("orderId", success ? orderId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/state")
    public ResponseEntity<Map<String, Object>> updateOrder(@RequestBody UpdateOrderDto updateOrderDto) {
        UUID orderId = orderService.updateOrder(updateOrderDto);
        boolean success = orderId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "주문 수정 성공" : "주문 수정 실패");
        response.put("orderId", success ? orderId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteOrder(@RequestBody DeleteOrderDto deleteOrderDto) {
        boolean success = orderService.deleteOrder(deleteOrderDto);

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "주문 삭제 성공" : "주문 삭제 실패");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOrder(@RequestParam("orderId") UUID orderId) {
        GetOrderDto getOrderDto = orderService.getOrder(orderId);
        boolean success = getOrderDto != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "주문 조회 성공" : "주문 조회 실패");
        response.put("orderInfo", getOrderDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/code")
    public ResponseEntity<Map<String, Object>> getOrderCode(@RequestParam("orderId") UUID orderId) {
        int code = orderService.getOrderCode(orderId);
        boolean success = code != -1;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "주문 코드 조회 성공" : "주문 코드 조회 실패");
        response.put("orderCode", code);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getOrderAll() {
        List<GetOrderDto> orderList = orderService.getOrderAll();
        boolean success = !orderList.isEmpty();

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "주문 전체 조회 성공" : "주문 전체 조회 실패");
        response.put("orderList", orderList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
