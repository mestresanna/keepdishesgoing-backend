package kdg.programming6.keepdishesgoing.order.domain;

public enum OrderStatus {
    PENDING,        // Just created, waiting for restaurant
    ACCEPTED,       // Restaurant accepted
    REJECTED,       // Restaurant rejected
    IN_PREPARATION, // Being prepared
    READY,          // Ready for pickup/delivery
    DELIVERED,      // Completed
    PICKED_UP
}
