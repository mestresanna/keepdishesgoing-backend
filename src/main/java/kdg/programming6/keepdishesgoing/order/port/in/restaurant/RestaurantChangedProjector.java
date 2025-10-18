package kdg.programming6.keepdishesgoing.order.port.in.restaurant;

public interface RestaurantChangedProjector {
    void projectCreatedRestaurant(CreatedRestaurantProjectionCommand command);
    void projectUpdatedRestaurant(UpdatedRestaurantProjectionCommand command);
}
