package kdg.programming6.keepdishesgoing.order.adapter.out.restaurant;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "restaurant_projection", schema = "orders")
public class RestaurantProjectionEntity {

    @Id
    @Column(name = "restaurant_id", nullable = false, unique = true)
    private UUID restaurantId;
    private UUID ownerId;
    private String name;
    private String street;
    private int number;
    private String city;
    private String country;
    private String pictureUrls;

    @Column(name = "opening_hours_json", columnDefinition = "TEXT")
    private String openingHoursJson;
    private String typeOfCuisine;
    private String contactEmail;
    private int defaultPreparationTimeInMinutes;
    private String priceRange;

    // Constructors
    protected RestaurantProjectionEntity() {
    }

    public RestaurantProjectionEntity(UUID restaurantId, UUID ownerId, String name, String street, int number,
                                      String city, String country, String pictureUrls, String openingHoursJson,
                                      String typeOfCuisine, String contactEmail,
                                      int defaultPreparationTimeInMinutes, String priceRange) {
        this.restaurantId = restaurantId;
        this.ownerId = ownerId;
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.pictureUrls = pictureUrls;
        this.openingHoursJson = openingHoursJson;
        this.typeOfCuisine = typeOfCuisine;
        this.contactEmail = contactEmail;
        this.defaultPreparationTimeInMinutes = defaultPreparationTimeInMinutes;
        this.priceRange = priceRange;
    }

    // Getters
    public UUID getRestaurantId() {
        return restaurantId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPictureUrls() {
        return pictureUrls;
    }

    public String getOpeningHoursJson() {
        return openingHoursJson;
    }

    public String getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public int getDefaultPreparationTimeInMinutes() {
        return defaultPreparationTimeInMinutes;
    }

    public String getPriceRange() {
        return priceRange;
    }
}
