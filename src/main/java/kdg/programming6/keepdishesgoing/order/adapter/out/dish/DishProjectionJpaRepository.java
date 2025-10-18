package kdg.programming6.keepdishesgoing.order.adapter.out.dish;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalDouble;

public interface DishProjectionJpaRepository extends JpaRepository<DishProjectionJpaEntity, DishProjectionId> {
    Optional<DishProjectionJpaEntity> findById(DishProjectionId dishId);
}
