package kdg.programming6.keepdishesgoing.common.events;


import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.common.vo.OrderItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderCreatedEvent(
        LocalDateTime eventPit,
        UUID orderId,
        UUID restaurantId,
        UUID customerId,
        String customerName,
        String customerEmail,
        Address deliveryAddress,
        List<OrderItemDto> items,
        BigDecimal totalPrice,
        String message
) implements DomainEvent {

    public OrderCreatedEvent(
            UUID orderId,
            UUID restaurantId,
            UUID customerId,
            String customerName,
            String customerEmail,
            Address deliveryAddress,
            List<OrderItemDto> items,
            BigDecimal totalPrice,
            String message) {
        this(
                LocalDateTime.now(),
                orderId,
                restaurantId,
                customerId,
                customerName,
                customerEmail,
                deliveryAddress,
                items,
                totalPrice,
                message
        );
    }
    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
