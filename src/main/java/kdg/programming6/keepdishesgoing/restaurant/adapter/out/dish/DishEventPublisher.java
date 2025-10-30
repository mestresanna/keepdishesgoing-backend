package kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DishEventPublisher implements SaveDishPort {
    private final ApplicationEventPublisher applicationEventPublisher;

    public DishEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void save(Dish dish){
        dish.getDomainEvents().forEach(applicationEventPublisher::publishEvent);
    }
}
