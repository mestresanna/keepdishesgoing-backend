package kdg.programming6.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantUpdatedEvent (LocalDateTime eventPit, UUID restaurant) implements DomainEvent{

    public RestaurantUpdatedEvent (LocalDateTime eventPit, UUID restaurant){
        this.eventPit = eventPit;
        this.restaurant = restaurant;
    }

    public RestaurantUpdatedEvent(UUID restaurant){
        this(LocalDateTime.now(), restaurant);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
