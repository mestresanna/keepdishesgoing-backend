package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "food_tags", schema = "restaurant")
public class FoodTagJpaEntity {
    @Id
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DishJpaEntity dish;

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

    public DishJpaEntity getDish() {
        return dish;
    }

    public void setDish(DishJpaEntity dish) {
        this.dish = dish;
    }
}
