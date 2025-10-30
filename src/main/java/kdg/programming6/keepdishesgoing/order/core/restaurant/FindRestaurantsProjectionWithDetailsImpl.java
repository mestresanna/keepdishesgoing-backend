package kdg.programming6.keepdishesgoing.order.core.restaurant;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindRestaurantDetailsUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.menu.LoadMenuProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.restaurant.LoadRestaurantsProjectionsPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindRestaurantsProjectionWithDetailsImpl implements FindRestaurantDetailsUseCase {
    private final LoadRestaurantsProjectionsPort loadRestaurantProjectionPort;
    private final LoadMenuProjectionPort loadMenuProjectionPort;

    public FindRestaurantsProjectionWithDetailsImpl(LoadRestaurantsProjectionsPort loadRestaurantProjectionPort, LoadMenuProjectionPort loadMenuProjectionPort) {
        this.loadRestaurantProjectionPort = loadRestaurantProjectionPort;
        this.loadMenuProjectionPort = loadMenuProjectionPort;
    }

    @Override
    @Transactional
    public RestaurantProjection findRestaurantDetails(UUID restaurantId) {
        return loadRestaurantProjectionPort.loadbyId(restaurantId).orElseThrow();
    }

    @Override
    @Transactional

    public List<MenuProjection> findAllMenusForRestaurant(UUID restaurantId) {
       return loadMenuProjectionPort.loadAllMenuProjections(restaurantId).orElseThrow();
    }

}
