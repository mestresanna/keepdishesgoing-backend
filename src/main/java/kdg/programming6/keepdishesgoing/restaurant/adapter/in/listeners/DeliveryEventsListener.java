package kdg.programming6.keepdishesgoing.restaurant.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.OrderPickedUpEvent;
import kdg.programming6.keepdishesgoing.restaurant.core.ManageOrderImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventsListener {

    private final ManageOrderImpl manageOrder;

    public DeliveryEventsListener(ManageOrderImpl manageOrder) {
        this.manageOrder = manageOrder;
    }

    @RabbitListener(queues = "delivery.order-picked-up")
    public void handleOrderPickedUp(OrderPickedUpEvent event) {
        manageOrder.markOrderPickedUp(event.orderId());
    }
}
