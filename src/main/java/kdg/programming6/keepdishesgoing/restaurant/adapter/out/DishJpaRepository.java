package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishJpaRepository extends JpaRepository<DishJpaEntity, UUID> {
}
