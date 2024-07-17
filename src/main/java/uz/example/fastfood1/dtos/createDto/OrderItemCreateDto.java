package uz.example.fastfood1.dtos.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemCreateDto {
    private UUID orderId;
    private UUID mealId;
    private int quantity;
}
