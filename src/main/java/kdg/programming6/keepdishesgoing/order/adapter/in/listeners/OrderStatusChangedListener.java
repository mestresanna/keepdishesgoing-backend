package kdg.programming6.keepdishesgoing.order.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.OrderStatusChangedEvent;
import kdg.programming6.keepdishesgoing.order.domain.OrderStatus;
import kdg.programming6.keepdishesgoing.order.port.out.LoadOrderPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveOrderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderStatusChangedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderStatusChangedListener.class);
    private final LoadOrderPort loadOrderPort;
    private final List<SaveOrderPort> saveOrderPort;

    public OrderStatusChangedListener(
            LoadOrderPort loadOrderPort,
            List<SaveOrderPort>  saveOrderPort) {
        this.loadOrderPort = loadOrderPort;
        this.saveOrderPort = saveOrderPort;
    }

    @ApplicationModuleListener
    public void handle(OrderStatusChangedEvent event) {
        logger.info("Order context received OrderStatusChangedEvent: {} -> {}",
                event.orderId(), event.newStatus());

        //TODO create a UseCase for this
        //Order order = loadOrderPort.findById(event.orderId())
//                .orElseThrow(() -> new IllegalStateException("Order not found: " + event.orderId()));
//
//        // Update order status
//        OrderStatus newStatus = OrderStatus.valueOf(event.newStatus());
//        order.updateStatus(newStatus, event.reason());
//
//        saveOrderPort.save(order);

        // TODO: Send notification to customer
        logger.info("Order {} status updated to: {}", event.orderId(), event.newStatus());
    }

}
