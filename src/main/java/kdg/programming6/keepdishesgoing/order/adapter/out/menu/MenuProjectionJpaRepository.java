package kdg.programming6.keepdishesgoing.order.adapter.out.menu;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuProjectionJpaRepository extends JpaRepository<MenuProjectionJpaEntity, UUID> {
    Optional<MenuProjectionJpaEntity> findById(UUID menuProjection);

    List<MenuProjectionJpaEntity> findByRestaurantId(UUID restaurantId);
}
