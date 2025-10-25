package kdg.programming6.keepdishesgoing.order.domain;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    UUID orderId;
    Basket basket;
    Customer customer;
    String message;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Order(UUID orderId, Basket basket, Customer customer, String message) {
        this.orderId = orderId;
        this.basket = basket;
        this.customer = customer;
        this.message = message;
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

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }
}
