package kdg.programming6.keepdishesgoing.restaurant.adapter.out.menu;

import kdg.programming6.keepdishesgoing.order.adapter.out.MenuProjectionJpaRepository;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveMenuPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MenuEventPublisher implements SaveMenuPort {
    private final ApplicationEventPublisher applicationEventPublisher;

    public MenuEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void save(Menu menu) {
        menu.getDomainEvents().forEach(applicationEventPublisher::publishEvent);
    }
}
