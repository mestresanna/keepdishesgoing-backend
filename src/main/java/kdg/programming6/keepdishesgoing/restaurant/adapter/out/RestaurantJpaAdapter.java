package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Repository
public class RestaurantJpaAdapter implements RestaurantPort {
    private final RestaurantJpaRepository jpaRepo;
    private final RestaurantMapper mapper;

    public RestaurantJpaAdapter(RestaurantJpaRepository jpaRepo, RestaurantMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(Restaurant restaurant) {
        RestaurantJpaEntity entity = mapper.toEntity(restaurant);
        jpaRepo.save(entity);
    }

    @Override
    public Optional<Restaurant> findById(UUID id) {
        return jpaRepo.findById(id)   // returns Optional<RestaurantJpaEntity>
                .map(mapper::toDomain); // convert entity -> domain
    }
}
