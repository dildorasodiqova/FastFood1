package uz.example.fastfood.service.mealService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.example.fastfood.dtos.createDto.MealCreateDto;
import uz.example.fastfood.dtos.createDto.OrderMealDTO;
import uz.example.fastfood.dtos.responcseDto.MealResponseDto;
import uz.example.fastfood.enties.MealEntity;
import uz.example.fastfood.exception.DataAlreadyExistsException;
import uz.example.fastfood.exception.DataNotFoundException;
import uz.example.fastfood.repository.MealRepository;
import uz.example.fastfood.service.categoryService.CategoryService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{
    private final ModelMapper modelMapper;
    private final MealRepository mealRepository;
    private final CategoryService categoryService;



    @Override
    public List<MealResponseDto> getAllMeals() {
        List<MealEntity> all = mealRepository.findAllByIsActiveTrue();
        return map(all);
    }

    private MealResponseDto map(MealEntity entity) {
        return modelMapper.map(entity, MealResponseDto.class);
    }
    private List<MealResponseDto> map(List<MealEntity> all) {
        return all.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public MealResponseDto getMealById(UUID id) {
        MealEntity meal = mealRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Meal not found"));
        return map(meal);
    }

    @Override
    public MealResponseDto createMeal(MealCreateDto dto) {
        if (mealRepository.finByName(dto.getName()).isPresent()){
            throw new DataAlreadyExistsException("Meal name already exists");
        }
        MealEntity save = mealRepository.save(modelMapper.map(dto, MealEntity.class));
        return map(save);
    }

    @Override
    public MealResponseDto updateMeal(UUID id, MealCreateDto dto) {
        MealEntity meal = mealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Meal not found with id: " + id));
        meal.setName(dto.getName());
        meal.setReadyTime(dto.getReadyTime());
        meal.setCost(dto.getCost());
        meal.setCategoryId(dto.getCategoryId());
        meal.setDescription(dto.getDescription());
        meal.setImage(dto.getImage());
        meal.setUpdateDate(LocalDateTime.now());
        mealRepository.save(meal);
        return map(meal);
    }


    @Override
    public void deleteMeal(UUID id) {
        mealRepository.softDeleteById(id);
    }

    @Override
    public BigDecimal calculateCost(List<OrderMealDTO> meals) {
        Long res = 0L;
        for (OrderMealDTO meal : meals) res += mealRepository.sumPrice(meal.getMealId(), meal.getAmount());
        return new BigDecimal(res);
    }

}
