package uz.example.fastfood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.example.fastfood.dtos.responcseDto.OrderItemResponseDto;
import uz.example.fastfood.service.orderItemService.OrderItemService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;


    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponseDto>> getOrderItemsByOrderId(@PathVariable UUID orderId) {
        List<OrderItemResponseDto> orderItems = orderItemService.getByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }
}
