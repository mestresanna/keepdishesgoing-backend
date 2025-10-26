package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.port.in.order.OrderChangeStatusCommand;

public interface UpdateOrderPort {
    void updateOrderStatus(Order order);
}
