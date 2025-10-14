package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadAllRestaurantsPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RestaurantJpaAdapter implements RestaurantPort, LoadRestaurantPort, LoadAllRestaurantsPort {
    private final RestaurantJpaRepository jpaRepo;
    private final RestaurantMapper mapper;
    private final OpeningHoursMapper openingHoursMapper;
    private Logger logger = LoggerFactory.getLogger(RestaurantJpaAdapter.class);

    public RestaurantJpaAdapter(RestaurantJpaRepository jpaRepo, RestaurantMapper mapper, OpeningHoursMapper openingHoursMapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
        this.openingHoursMapper = openingHoursMapper;
    }

    @Override
    public void save(Restaurant restaurant) {
        logger.info("Saving restaurant " + restaurant.getRestaurantId());
        if (jpaRepo.existsById(restaurant.getRestaurantId())) {
            RestaurantJpaEntity existing = jpaRepo.findById(restaurant.getRestaurantId())
                    .orElseThrow(() -> new RuntimeException("Restaurant not found in DB"));

            existing.setOpeningHoursJson(
                    openingHoursMapper.serializeOpeningHours(restaurant.getOpeningHours())
            );
            existing.setPriceRange(restaurant.getPriceRange().toString());
            jpaRepo.save(existing);
        } else {
            jpaRepo.save(mapper.toEntity(restaurant));
        }
    }

    @Override
    public Optional<Restaurant>  loadById(UUID id) {
        return jpaRepo.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Restaurant> loadAll() {
        return jpaRepo.findAll().stream().map(mapper::toDomain).toList();
    }
}
