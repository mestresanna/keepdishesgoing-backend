package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MenuJpaRepository extends JpaRepository<MenuJpaEntity, UUID>
{
    Optional<MenuJpaEntity> findByRestaurantId(UUID restaurantId);
}
