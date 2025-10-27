package kdg.programming6.keepdishesgoing.restaurant.port.in.order;

import java.util.UUID;

public interface ManageOrderUseCase {
    void acceptOrder(UUID orderId);
    void rejectOrder(UUID orderId, String reason);
    void startPreparation(UUID orderId);
    void markReady(UUID orderId);
    void markOrderPickedUp(UUID orderId);
    void markOrderDelivered(UUID orderId);
}