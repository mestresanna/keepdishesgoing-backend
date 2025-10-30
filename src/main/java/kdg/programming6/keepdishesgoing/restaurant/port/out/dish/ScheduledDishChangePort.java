package kdg.programming6.keepdishesgoing.restaurant.port.out.dish;

import kdg.programming6.keepdishesgoing.restaurant.domain.ScheduledDish;

import java.util.List;
import java.util.UUID;

public interface ScheduledDishChangePort {
    void save(ScheduledDish schedule);
    List<ScheduledDish> findAllPending();
    void markExecuted(UUID id);
}
