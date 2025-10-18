package kdg.programming6.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishStateChangedEvent (LocalDateTime eventPit, UUID dishId, String state)implements DomainEvent{
   public DishStateChangedEvent (UUID dishId, String state){
       this(LocalDateTime.now(), dishId, state);
   }
    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
