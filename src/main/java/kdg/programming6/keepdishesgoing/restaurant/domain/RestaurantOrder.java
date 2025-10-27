package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.events.OrderAcceptedEvent;
import kdg.programming6.keepdishesgoing.common.events.OrderReadyForPickupEvent;
import kdg.programming6.keepdishesgoing.common.events.OrderStatusChangedEvent;
import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.common.vo.Coordinates;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantOrder {
    private UUID orderId;
    private RestaurantId restaurantId;
    private String customerName;
    private Address deliveryAddress;
    private List<RestaurantOrderItem> items;
    private BigDecimal totalPrice;
    private RestaurantOrderStatus status;
    private String rejectionReason;
    private Instant createdAt;
    
    private final List<DomainEvent> domainEvents = new ArrayList<>();


    public RestaurantOrder(
            UUID orderId,
            RestaurantId restaurantId,
            String customerName,
            Address deliveryAddress,
            List<RestaurantOrderItem> items,
            BigDecimal totalPrice) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = RestaurantOrderStatus.PENDING;
        this.createdAt = Instant.now();
    }

    public static RestaurantOrder reconstruct(
            UUID orderId,
            RestaurantId restaurantId,
            String customerName,
            Address deliveryAddress,
            List<RestaurantOrderItem> items,
            BigDecimal totalPrice,
            RestaurantOrderStatus status,
            String rejectionReason,
            Instant createdAt) {
        RestaurantOrder order = new RestaurantOrder(
                orderId, restaurantId, customerName, deliveryAddress, items, totalPrice
        );
        order.status = status;
        order.rejectionReason = rejectionReason;
        order.createdAt = createdAt;
        return order;
    }

    public void accept(Address pickUpAddress, Coordinates pickUpCo, Address dropOffAddress, Coordinates dropOffCo) {
        if (status != RestaurantOrderStatus.PENDING) {
            throw new IllegalStateException("Can only accept pending orders");
        }
        this.status = RestaurantOrderStatus.ACCEPTED;
        
        addDomainEvent(new OrderStatusChangedEvent(
            orderId,
            "ACCEPTED",
            null,
            restaurantId.restaurantId()
        ));
        addDomainEvent(new OrderAcceptedEvent(restaurantId.restaurantId(), orderId, pickUpAddress, pickUpCo, dropOffAddress, dropOffCo));

    }

    public void reject(String reason) {
        if (status != RestaurantOrderStatus.PENDING) {
            throw new IllegalStateException("Can only reject pending orders");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Rejection reason is required");
        }
        
        this.status = RestaurantOrderStatus.REJECTED;
        this.rejectionReason = reason;
        
        addDomainEvent(new OrderStatusChangedEvent(
            orderId,
            "REJECTED",
            reason,
            restaurantId.restaurantId()
        ));
    }

    public void startPreparation() {
        if (status != RestaurantOrderStatus.ACCEPTED) {
            throw new IllegalStateException("Can only prepare accepted orders");
        }
        this.status = RestaurantOrderStatus.IN_PREPARATION;
        
        addDomainEvent(new OrderStatusChangedEvent(
            orderId,
            "IN_PREPARATION",
            null,
            restaurantId.restaurantId()
        ));
    }

    public void markReady() {
        if (status != RestaurantOrderStatus.IN_PREPARATION) {
            throw new IllegalStateException("Can only mark prepared orders as ready");
        }
        this.status = RestaurantOrderStatus.READY;
        
        addDomainEvent(new OrderStatusChangedEvent(
            orderId,
            "READY",
            null,
            restaurantId.restaurantId()
        ));
        addDomainEvent(new OrderReadyForPickupEvent(restaurantId.restaurantId(), orderId));
    }

    public void markPickedUp() {
        if (this.status != RestaurantOrderStatus.READY) {
            throw new IllegalStateException("Cannot mark order as picked up when not READY");
        }
        this.status = RestaurantOrderStatus.PICKED_UP;
        addDomainEvent(new OrderStatusChangedEvent(this.orderId,"PICKED_UP",null, restaurantId.restaurantId()));
    }


    // Getters
    public UUID getOrderId() { return orderId; }
    public RestaurantId getRestaurantId() { return restaurantId; }
    public RestaurantOrderStatus getStatus() { return status; }
    public String getRejectionReason() { return rejectionReason; }
    public List<DomainEvent> getDomainEvents() { return domainEvents; }

    public String getCustomerName() {
        return customerName;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<RestaurantOrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void addDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    }
    
    public void clearDomainEvents() {
        domainEvents.clear();
    }
}