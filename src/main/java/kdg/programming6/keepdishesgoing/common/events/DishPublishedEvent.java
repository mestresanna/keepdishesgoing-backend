package kdg.programming6.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishPublishedEvent(LocalDateTime eventPit, UUID dishId) implements DomainEvent {
    public DishPublishedEvent(LocalDateTime eventPit, UUID dishId) {
        this.eventPit = eventPit;
        this.dishId = dishId;
    }

    public DishPublishedEvent(UUID dishId){
        this(LocalDateTime.now(), dishId);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
