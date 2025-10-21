package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantJpaEntity, UUID>{
    List<RestaurantJpaEntity> findAll();
    Optional<RestaurantJpaEntity> findById(UUID id);
    List<RestaurantJpaEntity> findAllByOwner(UUID owner);
    void save(Restaurant restaurant);
}
