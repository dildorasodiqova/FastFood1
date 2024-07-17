package uz.example.fastfood.dtos.createDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreateDto {

    @NotEmpty(message = "Meals not empty!")
    private List<OrderMealDTO> meals;

    private LocationDto location;

}
