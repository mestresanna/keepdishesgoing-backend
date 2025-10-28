package kdg.programming6.keepdishesgoing.order.adapter.out.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PaymentStateRepository extends JpaRepository<PaymentStateJpaEntity, UUID> {
    Optional<PaymentStateJpaEntity> findByStripeSessionId(String stripeSessionId);
}
