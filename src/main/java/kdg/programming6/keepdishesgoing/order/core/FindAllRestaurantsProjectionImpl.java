package kdg.programming6.keepdishesgoing.order.core;

import kdg.programming6.keepdishesgoing.order.adapter.in.dto.RestaurantProjectionDto;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindAllRestaurantsProjectionsUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.LoadAllRestaurantsProjectionsPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRestaurantsProjectionImpl implements FindAllRestaurantsProjectionsUseCase {
    private final LoadAllRestaurantsProjectionsPort loadAllRestaurantsProjectionsPort;
    public FindAllRestaurantsProjectionImpl(LoadAllRestaurantsProjectionsPort loadAllRestaurantsProjectionsPort){
        this.loadAllRestaurantsProjectionsPort = loadAllRestaurantsProjectionsPort;
    }


    @Override
    public List<RestaurantProjection> findAllRestaurants() {
        return loadAllRestaurantsProjectionsPort.loadAllRestaurants();
    }
}

