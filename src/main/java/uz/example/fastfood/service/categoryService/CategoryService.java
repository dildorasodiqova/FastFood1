package uz.example.fastfood.service.categoryService;

import uz.example.fastfood.dtos.createDto.CategoryCreateDto;
import uz.example.fastfood.dtos.responcseDto.CategoryResponseDto;
import uz.example.fastfood.enties.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(UUID id);
    CategoryResponseDto createCategory(CategoryCreateDto createDto);
    void deleteCategory(UUID id);

    CategoryEntity findById(UUID categoryId);
}
