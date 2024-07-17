package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemResponseDto {
    private UUID id;
    private UUID mealId;
    private int quantity;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
