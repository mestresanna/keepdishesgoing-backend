package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import kdg.programming6.keepdishesgoing.restaurant.domain.Owner;

import java.util.UUID;

public record OwnerRequest(UUID ownerId, String name, String surname) {
    public Owner toDomain() {
        return new Owner(ownerId, name, surname);
    }
}