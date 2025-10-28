package kdg.programming6.keepdishesgoing.order.port.out.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;

import java.util.Optional;
import java.util.UUID;
public interface LoadOrderPort {
    Optional<Order> loadOrder(UUID orderId);
}
