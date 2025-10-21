package kdg.programming6.keepdishesgoing.restaurant.domain.ov;

import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.OwnerNotFoundException;

import java.util.UUID;

public record OwnerId (UUID ownerId) {
    public static OwnerId of(UUID ownerId) {
        return new OwnerId(ownerId);
    }

    public static void notOwnerFound(UUID ownerId) throws OwnerNotFoundException {
        throw new OwnerNotFoundException(String.format("Owner with UUID %s was not found", ownerId.toString()));
    }
}
