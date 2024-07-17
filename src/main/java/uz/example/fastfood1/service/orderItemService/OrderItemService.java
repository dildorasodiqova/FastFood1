package uz.example.fastfood1.service.orderItemService;

import uz.example.fastfood.dtos.responcseDto.OrderItemResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    List<OrderItemResponseDto> getByOrderId(UUID orderId);
}
