package kdg.programming6.keepdishesgoing.common.events;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record DishPublishedEvent(LocalDateTime eventPit, UUID dishId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl) implements DomainEvent {

    public DishPublishedEvent(LocalDateTime eventPit, UUID dishId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl) {
        this.eventPit = eventPit;
        this.dishId = dishId;
        this.name = name;
        this.restaurantId = restaurantId;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

    public DishPublishedEvent(UUID dishId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl){
        this(LocalDateTime.now(), dishId, name,  restaurantId, type, tags, description, price, pictureUrl);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
