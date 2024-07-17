package uz.example.fastfood.service.categoryService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.example.fastfood.dtos.createDto.CategoryCreateDto;
import uz.example.fastfood.dtos.responcseDto.CategoryResponseDto;
import uz.example.fastfood.enties.CategoryEntity;
import uz.example.fastfood.exception.DataAlreadyExistsException;
import uz.example.fastfood.exception.DataNotFoundException;
import uz.example.fastfood.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryEntity> all = categoryRepository.findAllByIsActiveTrue();
        return map(all);
    }

    @Override
    public CategoryResponseDto getCategoryById(UUID id) {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found."));
        return map(entity);
    }

    @Override
    public CategoryResponseDto createCategory(CategoryCreateDto dto) {
        if (categoryRepository.findByName(dto.getName()).isPresent()){
            throw  new DataAlreadyExistsException("Category with the same name already exists");
        }
        CategoryEntity save = categoryRepository.save(modelMapper.map(dto, CategoryEntity.class));
        return map(save);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.softDeleteById(id);
    }

    @Override
    public CategoryEntity findById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new DataNotFoundException("Category not found."));

    }

    private CategoryResponseDto map(CategoryEntity entity){
        return modelMapper.map(entity, CategoryResponseDto.class);
    }

    private List<CategoryResponseDto> map(List<CategoryEntity> all){
        return all.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }



}
