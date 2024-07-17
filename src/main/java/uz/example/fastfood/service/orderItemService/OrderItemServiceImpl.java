package uz.example.fastfood.service.orderItemService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.example.fastfood.dtos.responcseDto.OrderItemResponseDto;
import uz.example.fastfood.enties.OrderItemEntity;
import uz.example.fastfood.repository.OrderItemRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderItemResponseDto> getByOrderId(UUID orderId) {
        List<OrderItemEntity> orderItems = orderItemRepository.findAllByOrderId(orderId);
        return orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemResponseDto.class))
                .collect(Collectors.toList());
    }
}
