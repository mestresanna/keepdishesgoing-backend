package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface OwnerJpaRepository extends JpaRepository<OwnerJpaEntity, UUID> {
    OwnerJpaEntity save(OwnerJpaEntity owner);
}
