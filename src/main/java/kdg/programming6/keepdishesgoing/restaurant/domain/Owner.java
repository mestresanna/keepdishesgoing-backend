package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.events.OwnerCreatedEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Owner {
    private final UUID ownerId;
    private String name;
    private String surname;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Owner(UUID ownerId, String name, String surname) {
        this.ownerId = ownerId;
        this.name = name;
        this.surname = surname;
    }

    public static Owner create(String name, String surname) {
        Owner owner = new Owner(UUID.randomUUID(), name, surname);
        owner.recordEvent(new OwnerCreatedEvent(owner.getOwnerId()));
        return owner;
    }

    public UUID getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public String getSurname() { return surname; }

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }
}