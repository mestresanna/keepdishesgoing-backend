package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderStatus;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantOrderPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantOrderPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RestaurantOrderJpaAdapter implements SaveRestaurantOrderPort, LoadRestaurantOrderPort {
    private final RestaurantOrderJpaRepository restaurantOrderJpaRepository;
    private final RestaurantOrderMapper mapper;

    public RestaurantOrderJpaAdapter(RestaurantOrderJpaRepository restaurantOrderJpaRepository,
                                     RestaurantOrderMapper mapper) {
        this.restaurantOrderJpaRepository = restaurantOrderJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(RestaurantOrder restaurantOrder) {
        Optional<RestaurantOrderJpaEntity> existingEntity =
                restaurantOrderJpaRepository.findById(restaurantOrder.getOrderId());

        if (existingEntity.isPresent()) {
            // UPDATE existing entity
            RestaurantOrderJpaEntity entity = existingEntity.get();
            entity.setStatus(restaurantOrder.getStatus().name());
            entity.setRejectionReason(restaurantOrder.getRejectionReason());
            restaurantOrderJpaRepository.save(entity);
        } else {
            // CREATE new entity
            RestaurantOrderJpaEntity newEntity = mapper.toJpaEntity(restaurantOrder);
            restaurantOrderJpaRepository.save(newEntity);
        }
    }


    @Override
    public Optional<RestaurantOrder> findById(UUID restaurantOrderId) {
        return restaurantOrderJpaRepository.findById(restaurantOrderId)
                .map(mapper::toDomain);
    }

    @Override
    public List<RestaurantOrder> findAllByStatus(List<RestaurantOrderStatus> restaurantOrderStatuses, UUID restaurantId) {
        List<String> statusStrings = restaurantOrderStatuses.stream()
                .map(Enum::name)
                .toList();

        return restaurantOrderJpaRepository.findAllByStatusInAndRestaurantId(statusStrings, restaurantId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}