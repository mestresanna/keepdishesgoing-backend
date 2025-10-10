package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "food_tags")
public class FoodTagJpaEntity {
    @Id
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DishJpaEntity dish;
}
