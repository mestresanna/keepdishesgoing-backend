package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantOrderPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOrderEventPublisher implements SaveRestaurantOrderPort {
    private final ApplicationEventPublisher applicationEventPublisher;

    public RestaurantOrderEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void save(RestaurantOrder restaurantOrder) {
        restaurantOrder.getDomainEvents().forEach(applicationEventPublisher::publishEvent);
    }
}
