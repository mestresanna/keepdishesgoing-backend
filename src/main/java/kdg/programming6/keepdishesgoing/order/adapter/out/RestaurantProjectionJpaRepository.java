package kdg.programming6.keepdishesgoing.order.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantProjectionJpaRepository extends JpaRepository<RestaurantProjectionEntity, UUID> {
}
