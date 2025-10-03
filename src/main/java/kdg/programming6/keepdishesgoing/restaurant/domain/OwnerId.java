package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.OwnerNotFoundException;

import java.util.UUID;

public record OwnerId (UUID ownerId) {
    public static final OwnerId ANNA = OwnerId.of(UUID.fromString("31e3a110-37f1-4185-8a55-4d0c57963439"));

    public static OwnerId of(UUID ownerId) {
        return new OwnerId(ownerId);
    }

    public void notFound() throws OwnerNotFoundException {
        throw new OwnerNotFoundException(String.format("Owner with UUID %s was not found", this.ownerId));
    }
}
