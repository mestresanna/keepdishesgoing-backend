package kdg.programming6.keepdishesgoing.order.domain.projections;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuProjection {
    private UUID id;
    private UUID restaurantId;
    private List<DishProjection> dishes;
    private Double avgPrice;

    public MenuProjection(UUID id, UUID restaurantId, List<DishProjection> dishes, Double avgPrice) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.avgPrice = avgPrice;
    }
    public MenuProjection(UUID id, UUID restaurantId, Double avgPrice) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.avgPrice = avgPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public UUID getId() {
        return id;
    }
    public UUID getRestaurantId() {
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
