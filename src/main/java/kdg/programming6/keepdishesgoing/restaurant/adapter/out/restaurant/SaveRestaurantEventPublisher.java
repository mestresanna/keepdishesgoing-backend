package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SaveRestaurantEventPublisher implements SaveRestaurantPort {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Logger logger = LoggerFactory.getLogger(SaveRestaurantEventPublisher.class);

    public SaveRestaurantEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurant.getDomainEvents().forEach(publisher -> {
            logger.info("Saving restaurant " + publisher.toString());
            applicationEventPublisher.publishEvent(publisher);
                });
    }
}
