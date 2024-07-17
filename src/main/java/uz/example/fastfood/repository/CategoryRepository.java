package uz.example.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.example.fastfood.enties.CategoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByName(String name);

    List<CategoryEntity> findAllByIsActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE category c SET c.isActive = false WHERE c.id = :id")
    void softDeleteById(UUID id);
}
