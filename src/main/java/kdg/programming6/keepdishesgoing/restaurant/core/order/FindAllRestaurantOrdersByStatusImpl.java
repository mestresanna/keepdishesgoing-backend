package kdg.programming6.keepdishesgoing.restaurant.core.order;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderStatus;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.LoadRestaurantOrderByStatusUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.order.LoadRestaurantOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FindAllRestaurantOrdersByStatusImpl implements LoadRestaurantOrderByStatusUseCase {
    private final LoadRestaurantOrderPort loadRestaurantOrderPort;

    public FindAllRestaurantOrdersByStatusImpl(LoadRestaurantOrderPort loadRestaurantOrderPort) {
        this.loadRestaurantOrderPort = loadRestaurantOrderPort;
    }

    @Override
    public List<RestaurantOrder> findAllRestaurantOrderByStatusAndRestaurantId(List<RestaurantOrderStatus> restaurantOrders, UUID restaurantId) {
        return loadRestaurantOrderPort.findAllByStatusWithRestaurantId(restaurantOrders, restaurantId);
    }

    @Override
    public List<RestaurantOrder> findAllRestaurantOrderByStatus(RestaurantOrderStatus restaurantOrders) {
        return loadRestaurantOrderPort.findAllByStatus(restaurantOrders);
    }
}
