package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private UUID id;
    private UUID userId;
    private List<UUID> mealIds;
    private BigDecimal totalCost;
    private int estimatedDeliveryTime;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
