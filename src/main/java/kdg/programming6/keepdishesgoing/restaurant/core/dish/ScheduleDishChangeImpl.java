package kdg.programming6.keepdishesgoing.restaurant.core.dish;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishAction;
import kdg.programming6.keepdishesgoing.restaurant.domain.ScheduledDish;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.ScheduleDishChangeCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.ScheduleDishChangeUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.dish.ScheduledDishChangePort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleDishChangeImpl implements ScheduleDishChangeUseCase {
    private final ScheduledDishChangePort scheduledDishChangePort;


    public ScheduleDishChangeImpl(ScheduledDishChangePort scheduledDishChangePort){
        this.scheduledDishChangePort = scheduledDishChangePort;
    }
    @Override
    @Transactional
    public void scheduleDishChange(ScheduleDishChangeCommand command) {
        ScheduledDish schedule = ScheduledDish.schedule(
                command.dishIds(),
                DishAction.valueOf(command.action().toUpperCase()),
                command.scheduledAt()
        );
        scheduledDishChangePort.save(schedule);
    }

    @Override
    @Transactional
    public List<ScheduledDish> findAllPending() {
       return scheduledDishChangePort.findAllPending();
    }

    @Override
    @Transactional
    public void markAsExecuted(UUID dueSchedules){

        scheduledDishChangePort.markExecuted(dueSchedules);
    }
}

