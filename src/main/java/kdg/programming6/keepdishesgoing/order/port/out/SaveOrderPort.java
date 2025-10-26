package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.Order;

public interface SaveOrderPort {
    Order save(Order order);
}
