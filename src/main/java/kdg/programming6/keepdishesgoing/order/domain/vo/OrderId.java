package kdg.programming6.keepdishesgoing.order.domain.vo;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.OrderNotFoundException;

import java.util.UUID;

public record OrderId (UUID orderId) {
    public static OrderId of(UUID orderId) {
        return new OrderId(orderId);
    }

    public static void notOrderFound(UUID orderId) throws OrderNotFoundException {
        throw new OrderNotFoundException(String.format("Order with UUID %s was not found", orderId.toString()));
    }

}
