package kdg.programming6.keepdishesgoing.common.events;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record DishPublishedEvent(LocalDateTime eventPit, UUID dishId, UUID menuId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl, String status) implements DomainEvent {

    public DishPublishedEvent(LocalDateTime eventPit, UUID dishId,UUID menuId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl, String status) {
        this.eventPit = eventPit;
        this.dishId = dishId;
        this.menuId = menuId;
        this.name = name;
        this.restaurantId = restaurantId;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.status = status;
    }

    public DishPublishedEvent(UUID dishId,UUID menuId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl, String status){
        this(LocalDateTime.now(), dishId, menuId, name,  restaurantId, type, tags, description, price, pictureUrl, status);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
