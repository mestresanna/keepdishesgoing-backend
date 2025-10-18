package kdg.programming6.keepdishesgoing.order.adapter.out.menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MenuProjectionJpaRepository extends JpaRepository<MenuProjectionJpaEntity, UUID> {
    Optional<MenuProjectionJpaEntity> findById(UUID menuProjection);
}
