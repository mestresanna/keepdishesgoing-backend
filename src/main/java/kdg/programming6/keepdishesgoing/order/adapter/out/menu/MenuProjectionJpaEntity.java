package kdg.programming6.keepdishesgoing.order.adapter.out.menu;

import jakarta.persistence.*;
import kdg.programming6.keepdishesgoing.order.adapter.out.dish.DishProjectionJpaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menu_projections", schema = "orders")
public class MenuProjectionJpaEntity {

    @Id
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @Column(nullable = false)
    private UUID restaurantId;

    @Column(name = "avg_price")
    private Double avgPrice;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private List<DishProjectionJpaEntity> dishes = new ArrayList<>();

    protected MenuProjectionJpaEntity() {}
    public MenuProjectionJpaEntity(UUID menuId, UUID restaurantId, Double avgPrice) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.avgPrice = avgPrice;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public List<DishProjectionJpaEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishProjectionJpaEntity> dishes) {
        this.dishes = dishes;
    }
}
