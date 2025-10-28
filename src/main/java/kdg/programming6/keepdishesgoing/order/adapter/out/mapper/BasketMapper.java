package kdg.programming6.keepdishesgoing.order.adapter.out.mapper;
import kdg.programming6.keepdishesgoing.order.adapter.out.basket.BasketItemJpaEntity;
import kdg.programming6.keepdishesgoing.order.adapter.out.basket.BasketJpaEntity;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.BasketItems;
import kdg.programming6.keepdishesgoing.order.domain.BasketStatus;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BasketMapper {

    public BasketJpaEntity toEntity(Basket basket) {
        BasketJpaEntity entity = new BasketJpaEntity();
        entity.setBasketId(basket.getBasketId());
        entity.setRestaurantId(basket.getRestaurantId());
        entity.setTotalPrice(basket.getTotalPrice());
        entity.setStatus(basket.getBasketStatus().name());
        entity.setMessage(basket.getMessage());

        // Map basket items
        List<BasketItemJpaEntity> items = basket.getItems().stream()
                .map(item -> {
                    BasketItemJpaEntity itemEntity = new BasketItemJpaEntity(
                            basket.getBasketId(),
                            item.getDishId().menuId(), // assuming DishProjId has both menuId and dishId
                            item.getDishId().dishId(),
                            item.getPriceAtAddition(),
                            item.getQuantity(),
                            item.getDishName()
                    );
                    itemEntity.setBasket(entity);
                    return itemEntity;
                })
                .collect(Collectors.toList());

        entity.setItems(items);

        return entity;
    }

    public Basket toDomain(BasketJpaEntity entity) {
        List<BasketItems> items = entity.getItems().stream()
                .map(item -> new BasketItems(
                        new DishProjId(
                                item.getMenuId(),
                                item.getDishId()
                        ),
                        item.getNameDish(),
                        item.getPriceAtAdd(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new Basket(
                entity.getBasketId(),
                entity.getRestaurantId(),
                items,
                entity.getTotalPrice(),
                BasketStatus.valueOf(entity.getStatus()),
                entity.getMessage()
        );
    }
}
