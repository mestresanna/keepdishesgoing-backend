package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menus", schema = "restaurant")
public class MenuJpaEntity {
    @Id
    private UUID menuId;

    @Column(nullable = false)
    private UUID restaurantId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishesInMenuJpaEntity> menuDishes = new ArrayList<>();

    public UUID getMenuId() { return menuId; }
    public void setMenuId(UUID menuId) { this.menuId = menuId; }

    public UUID getRestaurantId() { return restaurantId; }
    public void setRestaurantId(UUID restaurantId) { this.restaurantId = restaurantId; }

    public List<DishesInMenuJpaEntity> getMenuDishes() { return menuDishes; }
    public void setMenuDishes(List<DishesInMenuJpaEntity> menuDishes) { this.menuDishes = menuDishes; }

    public void addDish(DishJpaEntity dish) {
        DishesInMenuJpaEntity menuDish = new DishesInMenuJpaEntity(this, dish);
        menuDishes.add(menuDish);
    }

    public void removeDish(DishJpaEntity dish) {
        menuDishes.removeIf(md -> md.getDish().equals(dish));
    }
}
