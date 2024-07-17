package uz.example.fastfood1.service.orderService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.example.fastfood.dtos.createDto.OrderCreateDto;
import uz.example.fastfood.dtos.responcseDto.BaseResponse;
import uz.example.fastfood.dtos.responcseDto.OrderResponseDto;
import uz.example.fastfood.enties.OrderEntity;
import uz.example.fastfood.enties.OrderItemEntity;
import uz.example.fastfood.enums.OrderStatus;
import uz.example.fastfood.repository.OrderItemRepository;
import uz.example.fastfood.repository.OrderRepository;
import uz.example.fastfood.service.mealService.MealService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final MealService mealService;

    private int calculateEstimatedDeliveryTime(int numberOfMeals, double distance) {
        int preparationTime = (int) Math.ceil(numberOfMeals / 4.0) * 5;
        int deliveryTime = (int) (distance * 3);
        return preparationTime + deliveryTime;
    }

    @Override
    public BaseResponse<?> makeOrder(OrderCreateDto dto) {
        OrderEntity order = new OrderEntity();
//        order.setUserId();
        order.setIsActive(true);
        order.setEstimatedDeliveryTime(calculateEstimatedDeliveryTime(dto.getMeals().size(), 3.0));
        order.setTotalCost(mealService.calculateCost(dto.getMeals()));
        order.setStatus(OrderStatus.PREPARING);
        orderRepository.save(order);

        List<OrderItemEntity> list = dto.getMeals()
                .stream()
                .map(item -> new OrderItemEntity(order.getId(), item.getMealId(), item.getAmount()))
                .toList();

        orderItemRepository.saveAll(list);
        return BaseResponse.successDefault();
    }

    @Override
    public BaseResponse<?> updateStatus(UUID orderId, OrderStatus status) {
        orderRepository.updateStatusById(status, orderId);
        return BaseResponse.successDefault();
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse<PageImpl<OrderResponseDto>> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderEntity> orderPage = orderRepository.findAllByIsActiveTrue(pageRequest);
        List<OrderResponseDto> orderDtos = orderPage.getContent().stream()
                .map(this::convertToDto)
                .toList();
        return  BaseResponse.success(new PageImpl<>(orderDtos, pageRequest, orderPage.getTotalElements()));
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse<PageImpl<OrderResponseDto>> getAllAsUser(UUID userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderEntity> orderPage = orderRepository.findAllByUserIdAndIsActiveTrue(userId, pageRequest);
        List<OrderResponseDto> orderDtos = orderPage.getContent().stream()
                .map(this::convertToDto)
                .toList();
        return BaseResponse.success(new PageImpl<>(orderDtos, pageRequest, orderPage.getTotalElements()));
    }

    private OrderResponseDto convertToDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderResponseDto.class);
    }

}
