package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderItem;

import java.util.List;

public class RestaurantOrderMapperDto {

    public static RestaurantOrderDto toDto(RestaurantOrder order) {
        List<RestaurantOrderItemDto> itemDtos = order.getItems().stream()
                .map(RestaurantOrderMapperDto::toItemDto)
                .toList();

        return new RestaurantOrderDto(
                order.getOrderId(),
                order.getRestaurantId().restaurantId(),
                order.getCustomerName(),
                order.getStatus().name(), // enum → String
                order.getRejectionReason(),
                order.getCreatedAt(),
                order.getTotalPrice(),
                itemDtos
        );
    }

    private static RestaurantOrderItemDto toItemDto(RestaurantOrderItem item) {
        return new RestaurantOrderItemDto(
                item.getDishId().dishId(),
                item.getDishName(),
                item.getQuantity(),
                item.getPriceAtOrder()
        );
    }
}
