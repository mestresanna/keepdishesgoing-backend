package kdg.programming6.keepdishesgoing.restaurant.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.order.OrderCreatedEvent;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.RestaurantOrderCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.RestaurantOrderItemCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.RestaurantOrderCreatedProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderCreatedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final RestaurantOrderCreatedProjector restaurantOrderCreatedProjector;

    public OrderCreatedListener(RestaurantOrderCreatedProjector saveRestaurantOrderPor){
        this.restaurantOrderCreatedProjector = saveRestaurantOrderPor;
    }

    @ApplicationModuleListener
    public void orderCreated(OrderCreatedEvent event) {
        logger.debug("OrderCreatedEvent received");
        List<RestaurantOrderItemCommand> items = event.items().stream()
                .map(item -> new RestaurantOrderItemCommand(
                        item.dishId(),
                        item.quantity(),
                        item.priceAtOrder()
                ))
                .collect(Collectors.toList());

        RestaurantOrderCommand restaurantOrder = new RestaurantOrderCommand(
                event.orderId(),
                event.restaurantId(),
                event.customerName(),
                event.deliveryAddress(),
                items,
                event.totalPrice()
        );
        restaurantOrderCreatedProjector.saveRestaurantOrder(restaurantOrder);
        logger.info("Restaurant order created: {}", restaurantOrder.orderId());
    }

}
