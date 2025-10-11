package kdg.programming6.keepdishesgoing.restaurant.port.in;

import java.util.UUID;

public interface UnpublishDishUseCase {
    void handle(UUID dishId);
}
