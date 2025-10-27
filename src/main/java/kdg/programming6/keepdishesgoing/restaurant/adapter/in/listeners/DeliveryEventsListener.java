package kdg.programming6.keepdishesgoing.restaurant.adapter.in.listeners;
import kdg.programming6.keepdishesgoing.common.events.DeliveryJobLocationUpdatedEvent;
import kdg.programming6.keepdishesgoing.common.events.OrderDeliveredEvent;
import kdg.programming6.keepdishesgoing.common.events.OrderPickedUpEvent;
import kdg.programming6.keepdishesgoing.restaurant.core.ManageOrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventsListener {

    private final ManageOrderImpl manageOrder;
    private final Logger logger = LoggerFactory.getLogger(DeliveryEventsListener.class);

    public DeliveryEventsListener(ManageOrderImpl manageOrder) {
        this.manageOrder = manageOrder;
    }

    @RabbitListener(queues = "delivery.order.pickedup")
    public void handleOrderPickedUp(OrderPickedUpEvent event) {
        logger.info("Received order picked up event" +  event);
        manageOrder.markOrderPickedUp(event.orderId());
    }

    @RabbitListener(queues = "delivery.order.location")
    public void orderLocationUpdated(DeliveryJobLocationUpdatedEvent event) {
        logger.info("Received location update: order={} lat={} lng={}", event.orderId(), event.lat(), event.lng());
        try{
            // Since PickUp event doesn't send a message
            manageOrder.markOrderPickedUp(event.orderId());
        } catch (Exception e){
            logger.debug("Cannot find location update for order {}: {}", event.orderId(), e.getMessage());
        }

    }



    @RabbitListener(queues = "delivery.order.delivered")
    public void orderDelivered(OrderDeliveredEvent event) {
        try {
            manageOrder.markOrderDelivered(event.orderId());
            logger.info("order delivered " + event);
        } catch (IllegalStateException e) {
            logger.warn("Cannot mark order {} as delivered: {}", event.orderId(), e.getMessage());
        }
    }
}
