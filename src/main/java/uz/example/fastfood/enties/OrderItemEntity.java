package uz.example.fastfood.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class OrderItemEntity extends BaseEntity {
    @Column(name = "order_id")
    private UUID orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    @Column(name = "meal_id")
    private UUID mealId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", referencedColumnName = "id")
    private MealEntity meal;

    private int quantity;

    public OrderItemEntity(UUID orderId, UUID mealId, int quantity) {
        this.orderId = orderId;
        this.mealId = mealId;
        this.quantity = quantity;
    }
}
