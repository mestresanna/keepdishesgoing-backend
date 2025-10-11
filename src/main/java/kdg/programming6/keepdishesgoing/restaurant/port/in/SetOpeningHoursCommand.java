package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;

import java.util.UUID;

public record SetOpeningHoursCommand (UUID restaurantId, UUID ownerId, OpeningHours openingHours){
}
