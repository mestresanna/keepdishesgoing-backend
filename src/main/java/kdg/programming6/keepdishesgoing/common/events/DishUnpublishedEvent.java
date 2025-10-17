package kdg.programming6.keepdishesgoing.common.events;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record DishUnpublishedEvent (LocalDateTime eventPit, UUID dishId) implements DomainEvent {

    public DishUnpublishedEvent(LocalDateTime eventPit, UUID dishId) {
        this.eventPit = eventPit;
        this.dishId = dishId;
    }

    public DishUnpublishedEvent(UUID dishId){
        this(LocalDateTime.now(), dishId);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
