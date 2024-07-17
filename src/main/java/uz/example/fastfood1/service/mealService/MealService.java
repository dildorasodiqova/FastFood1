package uz.example.fastfood1.service.mealService;

import uz.example.fastfood.dtos.createDto.MealCreateDto;
import uz.example.fastfood.dtos.createDto.OrderMealDTO;
import uz.example.fastfood.dtos.responcseDto.MealResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MealService {
    List<MealResponseDto> getAllMeals();
    MealResponseDto getMealById(UUID id);
    MealResponseDto createMeal(MealCreateDto dto);
    MealResponseDto updateMeal(UUID id, MealCreateDto dto);

    void deleteMeal(UUID id);

    BigDecimal calculateCost(List<OrderMealDTO> meals);
}
