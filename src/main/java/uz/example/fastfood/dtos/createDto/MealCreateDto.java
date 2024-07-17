package uz.example.fastfood.dtos.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealCreateDto {
    private String name;
    private int readyTime;
    private BigDecimal cost;
    private UUID categoryId;
    private String description;
    private String image;
}
