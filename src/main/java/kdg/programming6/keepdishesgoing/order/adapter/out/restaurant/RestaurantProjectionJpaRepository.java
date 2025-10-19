package kdg.programming6.keepdishesgoing.order.adapter.out.restaurant;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantProjectionJpaRepository extends JpaRepository<RestaurantProjectionEntity, UUID> {
    Optional<RestaurantProjectionEntity> findByRestaurantId(UUID restaurantId);
}
