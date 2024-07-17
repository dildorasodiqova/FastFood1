package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealResponseDto {
    private UUID id;
    private String name;
    private int readyTime;
    private BigDecimal cost;
    private UUID categoryId;
    private String description;
    private String image;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
