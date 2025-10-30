package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantOrderJpaRepository extends JpaRepository<RestaurantOrderJpaEntity, UUID> {
    List<RestaurantOrderJpaEntity> findAllByStatusInAndRestaurantId(List<String> statuses, UUID restaurantId);}
