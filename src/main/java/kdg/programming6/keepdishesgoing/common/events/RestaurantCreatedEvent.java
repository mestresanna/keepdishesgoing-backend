package kdg.programming6.keepdishesgoing.common.events;

import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantCreatedEvent(LocalDateTime eventPit,
                                     UUID restaurantId,
                                     UUID ownerId,
                                     String name,
                                     String street,
                                     int number,
                                     String city,
                                     String country,
                                     String pictureUrls,
                                     String openingHours,
                                     String typeOfCuisine,
                                     String contactEmail,
                                     int defaultPreparationTimeInMinutes,
                                     String priceRange) implements DomainEvent {

    public RestaurantCreatedEvent(LocalDateTime eventPit, UUID restaurantId, UUID ownerId, String name, String street, int number, String city, String country, String pictureUrls, String openingHours, String typeOfCuisine, String contactEmail, int defaultPreparationTimeInMinutes, String priceRange) {
        this.eventPit = eventPit;
        this.restaurantId = restaurantId;
        this.ownerId = ownerId;
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.pictureUrls = pictureUrls;
        this.openingHours = openingHours;
        this.typeOfCuisine = typeOfCuisine;
        this.contactEmail = contactEmail;
        this.defaultPreparationTimeInMinutes = defaultPreparationTimeInMinutes;
        this.priceRange = priceRange;
    }

    public RestaurantCreatedEvent(UUID restaurantId, UUID ownerId, String name, String street, int number, String city, String country, String pictureUrls, String openingHours, String typeOfCuisine, String contactEmail, int defaultPreparationTimeInMinutes, String priceRange) {
        this(LocalDateTime.now(), restaurantId, ownerId, name, street, number, city, country, pictureUrls, openingHours, typeOfCuisine, contactEmail, defaultPreparationTimeInMinutes, priceRange);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
