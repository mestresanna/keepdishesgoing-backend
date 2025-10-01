package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Owner {
    private final UUID ownerId;
    private String name;
    private String surname;

    public Owner(UUID ownerId, String name, String surname) {
        this.ownerId = ownerId;
        this.name = name;
        this.surname = surname;
    }


    public UUID getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
}