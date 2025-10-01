package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Repository;

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
        jpaRepo.save(mapper.toEntity(restaurant));
    }
}
