package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.Order;

import java.util.UUID;

public interface LoadOrderPort {
    Order loadOrder(UUID orderId);
}
