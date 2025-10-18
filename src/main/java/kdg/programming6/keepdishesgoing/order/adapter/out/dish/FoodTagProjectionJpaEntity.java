package kdg.programming6.keepdishesgoing.order.adapter.out.dish;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "food_tags", schema = "orders")
public class FoodTagProjectionJpaEntity {
    @Id
    private UUID id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "menu_id", referencedColumnName = "menu_id"),
            @JoinColumn(name = "dish_id", referencedColumnName = "dish_id")
    })
    private DishProjectionJpaEntity dish;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishProjectionJpaEntity getDish() {
        return dish;
    }

    public void setDish(DishProjectionJpaEntity dish) {
        this.dish = dish;
    }
}