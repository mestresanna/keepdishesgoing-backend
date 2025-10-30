package kdg.programming6.keepdishesgoing.common.events.dish;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishUnpublishedEvent (LocalDateTime eventPit, UUID dishId, UUID menuId) implements DomainEvent {

    public DishUnpublishedEvent(LocalDateTime eventPit, UUID dishId,  UUID menuId) {
        this.eventPit = eventPit;
        this.dishId = dishId;
        this.menuId = menuId;
    }

    public DishUnpublishedEvent(UUID dishId,  UUID menuId){
        this(LocalDateTime.now(), dishId, menuId);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
