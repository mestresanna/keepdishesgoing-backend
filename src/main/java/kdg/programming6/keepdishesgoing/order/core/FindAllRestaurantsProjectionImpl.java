package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindAllRestaurantsProjectionsUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.LoadRestaurantsProjectionsPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRestaurantsProjectionImpl implements FindAllRestaurantsProjectionsUseCase {
    private final LoadRestaurantsProjectionsPort loadRestaurantsProjectionsPort;
    public FindAllRestaurantsProjectionImpl(LoadRestaurantsProjectionsPort loadRestaurantsProjectionsPort){
        this.loadRestaurantsProjectionsPort = loadRestaurantsProjectionsPort;
    }


    @Override
    @Transactional
    public List<RestaurantProjection> findAllRestaurants() {
        return loadRestaurantsProjectionsPort.loadAllRestaurants();
    }
}

