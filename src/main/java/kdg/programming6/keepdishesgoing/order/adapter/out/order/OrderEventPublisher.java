package kdg.programming6.keepdishesgoing.order.adapter.out.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.port.out.SaveOrderPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher implements SaveOrderPort {
    private final ApplicationEventPublisher applicationEventPublisher;
    public OrderEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Order save(Order order) {
        order.getDomainEvents().forEach(applicationEventPublisher::publishEvent);
        return order;
    }
}
