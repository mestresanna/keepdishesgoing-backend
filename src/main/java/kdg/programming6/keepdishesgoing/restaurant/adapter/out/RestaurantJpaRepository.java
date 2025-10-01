package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantJpaEntity, UUID>{
    Optional<RestaurantJpaEntity> findById(UUID id);
    void save(Restaurant restaurant);
}
