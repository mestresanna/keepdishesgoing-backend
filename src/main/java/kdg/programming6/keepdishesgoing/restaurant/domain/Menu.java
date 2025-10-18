package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.events.MenuCreatedEvent;
import kdg.programming6.keepdishesgoing.common.events.MenuUpdatedEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Menu {
    private UUID id;
    private RestaurantId restaurantId;
    private List<Dish> dishes;
    private Double avgPrice;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Menu(UUID id, RestaurantId restaurantId) {
        this.id = id;
        this.restaurantId = restaurantId;
    }

    public static Menu create(RestaurantId restaurantId){
        Menu menu = new Menu(UUID.randomUUID(), restaurantId);
        menu.recordEvent(new MenuCreatedEvent(menu.id, menu.restaurantId.restaurantId()));
        return menu;
    }

    public void addDish(Dish dish) {
        if  (this.dishes != null) {
            if (dishes.stream().anyMatch(d -> d.getId().equals(dish.getId()))) {
                return;
            }
            if (getPublishedCount() >= 10 && dish.getState() == DishState.PUBLISHED)
                throw new IllegalStateException("Max 10 dishes can be live at once.");

        }
        if (this.dishes == null) {
            this.dishes = new ArrayList<Dish>();
        }
        this.dishes.add(dish);
        this.updateAvgPrice();
        recordEvent(new MenuUpdatedEvent(id, restaurantId.restaurantId(),  this.avgPrice ));
    }

    public void removeDish(Dish dish) {
        if (this.dishes != null) {
            this.dishes.removeIf(d -> d.getId().equals(dish.getId()));
            this.updateAvgPrice();
           recordEvent(new MenuUpdatedEvent(id, restaurantId.restaurantId(), this.avgPrice ));
        }
    }

    public Double updateAvgPrice() {
        this.avgPrice = this.getDishes().stream()
                .mapToDouble(Dish::getPrice)
                .average()
                .orElse(0.0);
        return avgPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = new ArrayList<>(dishes);
    }

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

    private long getPublishedCount() {
        if (dishes == null) {
            return 0;
        }
        return dishes.stream()
                .filter(d -> d.getState() == DishState.PUBLISHED)
                .count();
    }
}
