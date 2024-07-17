package uz.example.fastfood1.dtos.createDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class OrderMealDTO {

    private UUID mealId;

    private Integer amount;
}
