package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.order;

import java.time.Instant;
import java.util.UUID;

public record OrderStatusUpdateDto(
    UUID orderId,
    String status,
    String message,
    String rejectionReason,
    Instant updatedAt
) {
    public static OrderStatusUpdateDto accepted(UUID orderId) {
        return new OrderStatusUpdateDto(
            orderId,
            "ACCEPTED",
            "Order accepted successfully",
            null,
            Instant.now()
        );
    }
    
    public static OrderStatusUpdateDto rejected(UUID orderId, String reason) {
        return new OrderStatusUpdateDto(
            orderId,
            "REJECTED",
            "Order rejected",
            reason,
            Instant.now()
        );
    }
    
    public static OrderStatusUpdateDto inPreparation(UUID orderId) {
        return new OrderStatusUpdateDto(
            orderId,
            "IN_PREPARATION",
            "Order preparation started",
            null,
            Instant.now()
        );
    }
    
    public static OrderStatusUpdateDto ready(UUID orderId) {
        return new OrderStatusUpdateDto(
            orderId,
            "READY",
            "Order is ready for pickup/delivery",
            null,
            Instant.now()
        );
    }
}