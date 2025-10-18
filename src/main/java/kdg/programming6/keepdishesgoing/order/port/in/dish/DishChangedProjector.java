package kdg.programming6.keepdishesgoing.order.port.in.dish;

public interface DishChangedProjector {
    void projectPublishedDish(CreatedDishProjectionCommand command);
    void projectUnpublishDish(UnpublishDishProjectionCommand command);
    void projectChangedStateDish(UpdatedDishProjectionCommand command);

}
