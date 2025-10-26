package kdg.programming6.keepdishesgoing.restaurant.port.in.order;

import kdg.programming6.keepdishesgoing.common.vo.Address;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record RestaurantOrderCommand(UUID orderId, UUID restaurantId, String customerName, Address deliveryAddress, List<RestaurantOrderItemCommand> items, BigDecimal totalPrice) {
}
