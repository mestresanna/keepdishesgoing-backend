package kdg.programming6.keepdishesgoing.order.adapter.out.basket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BasketJpaRepository extends JpaRepository<BasketJpaEntity, UUID> {
}
