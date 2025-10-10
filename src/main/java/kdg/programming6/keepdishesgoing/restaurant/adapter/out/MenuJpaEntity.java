package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "menus")
public class MenuJpaEntity {
    @Id
    private UUID menuId;

    @Column(nullable = false)
    private UUID restaurantId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DishJpaEntity> dishes = new ArrayList<>();
}
