package kdg.programming6.keepdishesgoing.order.adapter.out.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, UUID> {
    Optional<OrderJpaEntity> findByOrderId(UUID orderId);
}
