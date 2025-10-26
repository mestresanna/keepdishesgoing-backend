package kdg.programming6.keepdishesgoing.order.adapter.out.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.port.out.LoadOrderPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveOrderPort;
import kdg.programming6.keepdishesgoing.order.port.out.UpdateOrderPort;
import kdg.programming6.keepdishesgoing.restaurant.adapter.out.order.RestaurantOrderJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderJpaAdapter implements SaveOrderPort, LoadOrderPort, UpdateOrderPort {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    public OrderJpaAdapter(OrderJpaRepository orderJpaRepository, OrderMapper orderMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = orderMapper.toJpaEntity(order);
        OrderJpaEntity savedEntity = orderJpaRepository.save(entity);
        return orderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> loadOrder(UUID orderId) {
        return orderJpaRepository.findById(orderId).map(orderMapper::toDomain);
    }

    @Override
    public void updateOrderStatus(Order order) {
        Optional<OrderJpaEntity> existingEntity =
                orderJpaRepository.findById(order.getOrderId());
        if (existingEntity.isPresent()) {
            // UPDATE existing entity
            OrderJpaEntity entity = existingEntity.get();
            entity.setStatus(order.getStatus().name());
            entity.setRejectionReason(order.getRejectionReason());
            orderJpaRepository.save(entity);
        }
    }
}
