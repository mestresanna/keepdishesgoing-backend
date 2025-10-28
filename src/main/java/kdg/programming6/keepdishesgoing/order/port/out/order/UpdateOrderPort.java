package kdg.programming6.keepdishesgoing.order.port.out.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;

public interface UpdateOrderPort {
    void updateOrderStatus(Order order);
}
