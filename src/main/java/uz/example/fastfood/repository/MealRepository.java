package uz.example.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.example.fastfood.enties.MealEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MealRepository extends JpaRepository<MealEntity, UUID> {
    List<MealEntity> findAllByIsActiveTrue();

    Optional<MealEntity> finByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE meals m SET m.isActive = false WHERE m.id = :id")
    void softDeleteById(UUID id);

    @Query("select sum((m.cost * ?2))  from meals m where m.id = ?1")
    Long sumPrice(UUID id, Integer amount1);
}
