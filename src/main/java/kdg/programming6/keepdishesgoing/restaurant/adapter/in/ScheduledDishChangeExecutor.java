package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

@Component
public class ScheduledDishChangeExecutor {

    private final ScheduledDishChangeRepository repository;
    private final DishChangedProjector dishChangedProjector;

    public ScheduledDishChangeExecutor(ScheduledDishChangeRepository repository,
                                       DishChangedProjector dishChangedProjector) {
        this.repository = repository;
        this.dishChangedProjector = dishChangedProjector;
    }

    @Scheduled(fixedRate = 60_000)
    public void processDueSchedules() {
        List<ScheduledDishChange> dueSchedules = repository.findAllPending();
        for (ScheduledDishChange schedule : dueSchedules) {
            for (UUID dishId : schedule.getDishIds()) {
                if (schedule.getAction() == DishAction.PUBLISH)
                    dishChangedProjector.projectPublishedDish(dishId);
                else
                    dishChangedProjector.projectUnpublishDish(dishId);
            }
            repository.delete(schedule.getId());
        }
    }
}
