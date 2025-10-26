package kdg.programming6.keepdishesgoing.restaurant.port.in.dish;

import java.util.UUID;

public interface PublishDishUseCase {
    void publishDish(UUID dishId);
}
