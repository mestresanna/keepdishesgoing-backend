package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.BasketItems;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketMapperDto {

    public BasketDto toDto(Basket basket) {
        List<BasketItemDto> items = basket.getItems().stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());

        return new BasketDto(
                basket.getBasketId(),
                basket.getRestaurantId(),
                items,
                basket.getTotalPrice(),
                basket.getItems().size()
        );
    }

    private BasketItemDto toItemDto(BasketItems item) {
        return new BasketItemDto(
                item.getDishId().dishId(),
                item.getDishName(),
                item.getQuantity(),
                item.getPriceAtAddition(),
                item.totalPrice()  // quantity * priceAtAddition
        );
    }
}