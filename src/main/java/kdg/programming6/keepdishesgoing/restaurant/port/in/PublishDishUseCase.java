package kdg.programming6.keepdishesgoing.restaurant.port.in;

import java.util.UUID;

public interface PublishDishUseCase {
    void handle(UUID dishId);
}
