package kdg.programming6.keepdishesgoing.order.adapter.out.dish;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.UUID;

public interface DishProjectionJpaRepository extends JpaRepository<DishProjectionJpaEntity, DishProjectionId> {
    Optional<DishProjectionJpaEntity> findById(DishProjectionId dishId);
    List<DishProjectionJpaEntity> findById_DishId(UUID dishId);
    List<DishProjectionJpaEntity> findById_MenuId(UUID menuId);

}
