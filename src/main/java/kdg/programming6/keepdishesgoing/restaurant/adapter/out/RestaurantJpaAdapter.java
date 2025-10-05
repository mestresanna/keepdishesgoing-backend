package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RestaurantJpaAdapter implements RestaurantPort, LoadRestaurantPort {
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
    public Optional<Restaurant>  loadById(UUID id) {
        return jpaRepo.findById(id)   // returns Optional<RestaurantJpaEntity>
                .map(mapper::toDomain); // convert entity -> domain
    }

    @Override
    public List<Restaurant> loadAll() {
        return jpaRepo.findAll().stream().map(mapper::toDomain).toList();
    }
}
