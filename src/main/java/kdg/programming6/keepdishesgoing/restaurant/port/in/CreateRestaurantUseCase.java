package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

public interface CreateRestaurantUseCase {
    void handle(CreateRestaurantCommand command);
}
