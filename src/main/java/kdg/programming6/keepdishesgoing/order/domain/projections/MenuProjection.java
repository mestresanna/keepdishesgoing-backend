package kdg.programming6.keepdishesgoing.order.domain.projections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuProjection {
    private UUID id;
    private RestaurantProjectionId restaurantId;
    private List<DishProjection> dishes;
    private BigDecimal avgPrice;

    public MenuProjection(UUID id, RestaurantProjectionId restaurantId, List<DishProjection> dishes, BigDecimal avgPrice) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.avgPrice = avgPrice;
    }
    public MenuProjection(UUID id, RestaurantProjectionId restaurantId, BigDecimal avgPrice) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.avgPrice = avgPrice;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public UUID getId() {
        return id;
    }
    public RestaurantProjectionId getRestaurantId() {
        return restaurantId;
    }
    public List<DishProjection> getDishes() {
        return dishes;
    }


    public void addDish(DishProjection dishProjection) {
        if (dishProjection == null) {
            throw new IllegalArgumentException("DishProjection cannot be null");}
        if (dishes == null) {
            dishes = new ArrayList<>();}
        boolean exists = dishes.stream()
                .anyMatch(d -> d.getDishId().equals(dishProjection.getDishId()));
        if (exists) {
            // Optional: log a warning or update existing dish if needed
            return;
        }
        dishes.add(dishProjection);
    }

    public void deleteDish(DishProjection dishProjection) {
        if (dishProjection == null || dishes == null || dishes.isEmpty()) {
            return;
        }
        boolean removed = dishes.removeIf(d -> d.getDishId().equals(dishProjection.getDishId()));
    }

}
