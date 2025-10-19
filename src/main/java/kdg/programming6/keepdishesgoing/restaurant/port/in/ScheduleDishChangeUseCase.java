package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.ScheduledDish;

import java.util.List;
import java.util.UUID;

public interface ScheduleDishChangeUseCase {
    void scheduleDishChange(ScheduleDishChangeCommand command);
    List<ScheduledDish> findAllPending();
    void markAsExecuted(UUID dueSchedules);
}
