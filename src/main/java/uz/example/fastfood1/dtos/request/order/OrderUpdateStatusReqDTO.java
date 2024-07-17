package uz.example.fastfood1.dtos.request.order;

import lombok.Getter;
import lombok.Setter;
import uz.example.fastfood.enums.OrderStatus;

import java.util.UUID;


@Getter
@Setter
public class OrderUpdateStatusReqDTO {
    private UUID orderId;
    private OrderStatus status;
}
