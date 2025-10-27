package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantOrderMapper {

    public RestaurantOrderJpaEntity toJpaEntity(RestaurantOrder domain) {
        RestaurantOrderJpaEntity entity = new RestaurantOrderJpaEntity();

        entity.setOrderId(domain.getOrderId());
        entity.setRestaurantId(domain.getRestaurantId().restaurantId());
        entity.setCustomerName(domain.getCustomerName());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setTotalPrice(domain.getTotalPrice());
        entity.setStatus(domain.getStatus().name());
        entity.setRejectionReason(domain.getRejectionReason());

        Address address = domain.getDeliveryAddress();
        entity.setDeliveryStreet(address.street());
        entity.setDeliveryNumber(address.number());
        entity.setDeliveryPostalCode(address.postalCode());
        entity.setDeliveryCity(address.city());
        entity.setDeliveryCountry(address.country());

        List<RestaurantOrderItemJpaEntity> itemEntities = domain.getItems().stream()
                .map(item -> {
                    RestaurantOrderItemJpaEntity itemEntity = new RestaurantOrderItemJpaEntity();
                    itemEntity.setDishId(item.getDishId().dishId());
                    itemEntity.setDishName(item.getDishName());
                    itemEntity.setPriceAtOrder(item.getPriceAtOrder());
                    itemEntity.setQuantity(item.getQuantity());
                    return itemEntity;
                })
                .collect(Collectors.toList());

        entity.setItems(itemEntities);

        return entity;
    }

    public RestaurantOrder toDomain(RestaurantOrderJpaEntity entity) {
        Address address = new Address(
                entity.getDeliveryStreet(),
                entity.getDeliveryNumber(),
                entity.getDeliveryCity(),
                entity.getDeliveryPostalCode(),
                entity.getDeliveryCountry()
        );

        List<RestaurantOrderItem> items = entity.getItems().stream()
                .map(itemEntity -> new RestaurantOrderItem(
                        new DishId(itemEntity.getDishId()),
                        itemEntity.getDishName(),
                        itemEntity.getQuantity(),
                        itemEntity.getPriceAtOrder()
                ))
                .collect(Collectors.toList());

        return RestaurantOrder.reconstruct(
                entity.getOrderId(),
                new RestaurantId(entity.getRestaurantId()),
                entity.getCustomerName(),
                address,
                items,
                entity.getTotalPrice(),
                RestaurantOrderStatus.valueOf(entity.getStatus()),
                entity.getRejectionReason(),
                entity.getCreatedAt()
        );
    }

}
