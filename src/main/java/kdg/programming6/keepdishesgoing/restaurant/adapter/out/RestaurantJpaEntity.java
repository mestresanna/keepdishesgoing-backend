package kdg.programming6.keepdishesgoing.restaurant.adapter.out;
import jakarta.persistence.*;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import kdg.programming6.keepdishesgoing.restaurant.domain.OwnerId;

@Entity
@Table(name="restaurants", schema = "restaurant")
public class RestaurantJpaEntity {
    @Id
    private UUID restaurantId;

    private UUID owner;

    private String name;

    @Email
    private String contactEmail;
    private String typeOfCuisine;

    @Column(columnDefinition = "TEXT")
    private String openingHoursJson;

    private String street;
    private int number;
    private String city;
    private String country;

    private String pictureUrls;
    private int defaultPreparationTimeInMinutes;
    private String priceRange;

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public void setTypeOfCuisine(String typeOfCuisine) {
        this.typeOfCuisine = typeOfCuisine;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(String pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public int getDefaultPreparationTimeInMinutes() {
        return defaultPreparationTimeInMinutes;
    }

    public void setDefaultPreparationTimeInMinutes(int defaultPreparationTimeInMinutes) {
        this.defaultPreparationTimeInMinutes = defaultPreparationTimeInMinutes;
    }
    public String getOpeningHoursJson() { return openingHoursJson; }
    public void setOpeningHoursJson(String openingHoursJson) { this.openingHoursJson = openingHoursJson; }

}
