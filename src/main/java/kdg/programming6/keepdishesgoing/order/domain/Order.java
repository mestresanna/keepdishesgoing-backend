package kdg.programming6.keepdishesgoing.order.domain;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.events.OrderCreatedEvent;
import kdg.programming6.keepdishesgoing.common.vo.OrderItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private UUID orderId;
    private Basket basket;
    private Customer customer;
    private String message;
    private OrderStatus status;
    private String rejectionReason;
    private BigDecimal totalPrice;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Order(UUID orderId, Basket basket, Customer customer, String message, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.basket = basket;
        this.customer = customer;
        this.message = message;
        this.status = OrderStatus.PENDING; // Initial status
        this.totalPrice = totalPrice;
        this.addDomainEvent(createOrderCreatedEvent());
    }
    public static Order reconstitute(
            UUID orderId,
            Basket basket,
            Customer customer,
            String message,
            BigDecimal totalPrice,
            OrderStatus status,
            String rejectionReason) {
        Order order = new Order(orderId, basket, customer, message, totalPrice);
        order.status = status;
        order.rejectionReason = rejectionReason;
        order.clearDomainEvents();
        return order;
    }

    private OrderCreatedEvent createOrderCreatedEvent() {
        List<OrderItemDto> items = basket.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getDishId().dishId(),
                        item.getQuantity(),
                        item.getPriceAtAddition()
                ))
                .collect(Collectors.toList());

        return new OrderCreatedEvent(
                orderId,
                basket.getRestaurantId(),
                null, // customerId if you have it
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                items,
                basket.getTotalPrice(),
                message
        );
    }
    public void updateStatus(OrderStatus newStatus, String reason) {
        this.status = newStatus;
        if (newStatus == OrderStatus.REJECTED) {
            this.rejectionReason = reason;
        }
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Basket getBasket() {
        return basket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getMessage() {
        return message;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getRejectionReason() { return rejectionReason; }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
