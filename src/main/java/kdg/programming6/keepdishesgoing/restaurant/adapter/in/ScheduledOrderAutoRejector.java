package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderStatus;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.LoadRestaurantOrderByStatusUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.ManageOrderUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledOrderAutoRejector {

    private final LoadRestaurantOrderByStatusUseCase loadRestaurantOrderByStatusUseCase;
    private final ManageOrderUseCase manageOrderUseCase;

    public ScheduledOrderAutoRejector(LoadRestaurantOrderByStatusUseCase loadRestaurantOrderByStatusUseCase,
                                      ManageOrderUseCase manageOrderUseCase) {
        this.loadRestaurantOrderByStatusUseCase = loadRestaurantOrderByStatusUseCase;
        this.manageOrderUseCase = manageOrderUseCase;
    }

    @Scheduled(fixedRate = 60_000)
    void autoRejectUnattendedOrders() {
        RestaurantOrderStatus statuses = RestaurantOrderStatus.PENDING;
        List<RestaurantOrder> pendingOrders =
                loadRestaurantOrderByStatusUseCase.findAllRestaurantOrderByStatus(statuses);

        Instant now = Instant.now();
        List<RestaurantOrder> expiredOrders = pendingOrders.stream()
                .filter(order -> order.getCreatedAt().isBefore(now.minus(Duration.ofMinutes(5))))
                .collect(Collectors.toList());

        for (RestaurantOrder order : expiredOrders) {
            manageOrderUseCase.rejectOrder(order.getOrderId(), "Automatically rejected after 5 minutes of inactivity.");
        }
    }
}
