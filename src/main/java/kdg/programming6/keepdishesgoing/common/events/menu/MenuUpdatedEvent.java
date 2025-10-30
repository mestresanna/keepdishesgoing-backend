package kdg.programming6.keepdishesgoing.common.events.menu;


import kdg.programming6.keepdishesgoing.common.events.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MenuUpdatedEvent(LocalDateTime eventPit, UUID menuId, UUID restaurantId, BigDecimal avgPrice) implements DomainEvent {
    public MenuUpdatedEvent(UUID menuId, UUID restaurantId, BigDecimal avgPrice) {
        this(LocalDateTime.now(), menuId, restaurantId, avgPrice);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
