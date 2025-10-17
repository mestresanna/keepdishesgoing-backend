package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEventPublisher implements RestaurantPort {
    private final ApplicationEventPublisher applicationEventPublisher;

    public RestaurantEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurant.getDomainEvents().forEach(applicationEventPublisher::publishEvent);
    }
}
