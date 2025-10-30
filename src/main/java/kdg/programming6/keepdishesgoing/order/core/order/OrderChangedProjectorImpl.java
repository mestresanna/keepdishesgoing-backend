package kdg.programming6.keepdishesgoing.order.core.order;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.OrderStatus;
import kdg.programming6.keepdishesgoing.order.port.in.order.OrderChangeStatusCommand;
import kdg.programming6.keepdishesgoing.order.port.in.order.OrderChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.out.order.LoadOrderPort;
import kdg.programming6.keepdishesgoing.order.port.out.order.UpdateOrderPort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderChangedProjectorImpl implements OrderChangedProjector {
    private final LoadOrderPort loadOrderPort;
    private final UpdateOrderPort saveOrderPort;

    public OrderChangedProjectorImpl(LoadOrderPort loadOrderPort, UpdateOrderPort saveOrderPort) {
        this.loadOrderPort = loadOrderPort;
        this.saveOrderPort = saveOrderPort;
    }

    @Override
    public void updateOrderStatus(OrderChangeStatusCommand command) {
        Order order = loadOrderPort.loadOrder(command.orderId())
                .orElseThrow(() -> new IllegalStateException("Order not found: " + command.orderId()));

        OrderStatus newStatus = OrderStatus.valueOf(command.status());
        order.updateStatus(newStatus, command.reason());
        saveOrderPort.updateOrderStatus(order);
    }
}
