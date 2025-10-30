package kdg.programming6.keepdishesgoing.order.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.order.OrderStatusChangedEvent;
import kdg.programming6.keepdishesgoing.order.port.in.order.OrderChangeStatusCommand;
import kdg.programming6.keepdishesgoing.order.port.in.order.OrderChangedProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusChangedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderStatusChangedListener.class);
    private final OrderChangedProjector orderChangedProjector;


    public OrderStatusChangedListener(OrderChangedProjector orderChangedProjector) {
        this.orderChangedProjector = orderChangedProjector;

    }

    @ApplicationModuleListener
    public void handle(OrderStatusChangedEvent event) {
        logger.info("Order context received OrderStatusChangedEvent: {} -> {}",
                event.orderId(), event.newStatus());
        orderChangedProjector.updateOrderStatus(new OrderChangeStatusCommand(event.orderId(), event.newStatus(), event.reason()));
    }

}
