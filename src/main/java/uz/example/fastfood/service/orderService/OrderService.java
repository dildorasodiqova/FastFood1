package uz.example.fastfood.service.orderService;

import org.springframework.data.domain.PageImpl;
import uz.example.fastfood.dtos.createDto.OrderCreateDto;
import uz.example.fastfood.dtos.responcseDto.BaseResponse;
import uz.example.fastfood.dtos.responcseDto.OrderResponseDto;
import uz.example.fastfood.enums.OrderStatus;

import java.util.UUID;

public interface OrderService {
    BaseResponse<?> makeOrder(OrderCreateDto dto);
    BaseResponse<?> updateStatus(UUID orderId, OrderStatus status);

    BaseResponse<PageImpl<OrderResponseDto>> getAll(int page, int size);

    BaseResponse<PageImpl<OrderResponseDto>> getAllAsUser(UUID userId, int page, int size);
}
