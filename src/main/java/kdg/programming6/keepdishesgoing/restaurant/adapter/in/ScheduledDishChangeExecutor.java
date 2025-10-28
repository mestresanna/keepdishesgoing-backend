package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.DishAction;
import kdg.programming6.keepdishesgoing.restaurant.domain.ScheduledDish;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.PublishDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.ScheduleDishChangeUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.UnpublishDishUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ScheduledDishChangeExecutor {

    private final ScheduleDishChangeUseCase scheduledDishUseCase;
    private final PublishDishUseCase publishDishUseCase;
    private final UnpublishDishUseCase unpublishDishUseCase;

    public ScheduledDishChangeExecutor(ScheduleDishChangeUseCase scheduledDishUseCase,PublishDishUseCase publishDishUseCase, UnpublishDishUseCase unpublishDishUseCase) {
        this.scheduledDishUseCase = scheduledDishUseCase;
        this.publishDishUseCase = publishDishUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
    }

    @Scheduled(fixedRate = 60_000)
    public void processDueSchedules() {
        List<ScheduledDish> dueSchedules = scheduledDishUseCase.findAllPending();
        for (ScheduledDish schedule : dueSchedules) {
            for (UUID dishId : schedule.getDishIds()) {
                if (schedule.getAction() == DishAction.PUBLISHED){
                    publishDishUseCase.publishDish(dishId);
                }
                else{
                    unpublishDishUseCase.unplublishDish(dishId);
                }
            }
            scheduledDishUseCase.markAsExecuted(schedule.getId());
        }
    }
}
