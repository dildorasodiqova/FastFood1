package uz.example.fastfood1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.example.fastfood.enties.OrderEntity;
import uz.example.fastfood.enums.OrderStatus;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Transactional
    @Modifying
    @Query("update orderEntity o set o.status = ?1 where o.id = ?2")
    void updateStatusById(OrderStatus status, UUID id);



    Page<OrderEntity> findAllByUserIdAndIsActiveTrue(UUID userId, PageRequest pageRequest);

    Page<OrderEntity> findAllByIsActiveTrue(PageRequest pageRequest);
}

