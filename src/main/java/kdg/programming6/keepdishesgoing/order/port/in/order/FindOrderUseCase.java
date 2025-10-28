package kdg.programming6.keepdishesgoing.order.port.in.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;

import java.util.UUID;

public interface FindOrderUseCase {
    Order findById(UUID restaurantOrderId);
}
