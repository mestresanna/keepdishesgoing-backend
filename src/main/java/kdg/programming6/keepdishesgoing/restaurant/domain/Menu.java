package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Menu {
    private UUID id;
    private RestaurantId restaurantId;
    private List<Dish> dishes;

    public void addDish(Dish dish) {
        if (getPublishedCount() >= 10 && dish.getState() == DishState.PUBLISHED)
            throw new IllegalStateException("Max 10 dishes can be live at once.");
        dishes.add(dish);
    }

    public void applyAllPendingChanges() {
        for (Dish dish : dishes) {
            if (dish.hasPendingChanges()) dish.publish();
        }
    }

    public void scheduleChanges(LocalDateTime when) {
        for (Dish dish : dishes) {
            if (dish.hasPendingChanges()) {
                dish.scheduleChange(new PublishDishesSchedule(when));
            }
        }
    }

    private long getPublishedCount() {
        return dishes.stream()
                .filter(d -> d.getState() == DishState.PUBLISHED)
                .count();
    }
}
