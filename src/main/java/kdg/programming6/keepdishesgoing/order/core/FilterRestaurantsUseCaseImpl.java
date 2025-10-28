package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FilterRestaurantsUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.restaurant.LoadRestaurantsProjectionsPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterRestaurantsUseCaseImpl implements FilterRestaurantsUseCase {

    private final LoadRestaurantsProjectionsPort restaurantPort;

    public FilterRestaurantsUseCaseImpl(LoadRestaurantsProjectionsPort restaurantPort) {
        this.restaurantPort = restaurantPort;
    }

    @Override
    @Transactional
    public List<RestaurantProjection> filterRestaurants(String cuisine, String priceRange) {
        return restaurantPort.loadAllRestaurants().stream()
                .filter(r -> r.matches(cuisine, priceRange))
                .toList();
    }

}
