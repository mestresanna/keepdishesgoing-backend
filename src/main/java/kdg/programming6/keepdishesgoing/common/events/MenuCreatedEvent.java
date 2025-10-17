package kdg.programming6.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record MenuCreatedEvent (LocalDateTime eventPit, UUID menuId, UUID restaurantId) implements DomainEvent {

    public MenuCreatedEvent(LocalDateTime eventPit, UUID menuId, UUID restaurantId) {
        this.eventPit = eventPit;
        this.menuId = menuId;
        this.restaurantId = restaurantId;
    }

    public MenuCreatedEvent(UUID menuId, UUID restaurantId){
        this(LocalDateTime.now(), menuId,  restaurantId);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}