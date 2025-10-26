package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderItem;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.LoadRestaurantOrderPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantOrderPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RestaurantOrderJpaAdapter implements SaveRestaurantOrderPort, LoadRestaurantOrderPort {
    private final RestaurantOrderJpaRepository restaurantOrderJpaRepository;
    private final RestaurantOrderMapper mapper;

    public RestaurantOrderJpaAdapter(RestaurantOrderJpaRepository restaurantOrderJpaRepository,
                                     RestaurantOrderMapper mapper) {
        this.restaurantOrderJpaRepository = restaurantOrderJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(RestaurantOrder restaurantOrder) {
        RestaurantOrderJpaEntity entity = mapper.toJpaEntity(restaurantOrder);
        restaurantOrderJpaRepository.save(entity);
    }

    @Override
    public Optional<RestaurantOrder> findById(UUID restaurantOrderId) {
        return restaurantOrderJpaRepository.findById(restaurantOrderId)
                .map(mapper::toDomain);
    }
}