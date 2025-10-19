package kdg.programming6.keepdishesgoing.order.domain.projections;

import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class RestaurantProjection {
    private UUID restaurantId;
    private UUID owner;
    private String name;

    private String street;
    private int number;
    private String city;
    private String country;

    private String pictureUrls;
    private OpeningHoursProjection openingHours;    private String typeOfCuisine;
    private String contactEmail;
    private int defaultPreparationTimeInMinutes;
    private String priceRange;

    public RestaurantProjection(UUID restaurantId, UUID owner, String name, String street, int number, String city, String country, String pictureUrls, OpeningHoursProjection openingHours, String typeOfCuisine, String contactEmail, int defaultPreparationTimeInMinutes, String priceRange) {
        this.restaurantId = restaurantId;
        this.owner = owner;
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

    public String getPriceRange() {
        return priceRange;
    }

    public int getDefaultPreparationTimeInMinutes() {
        return defaultPreparationTimeInMinutes;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public OpeningHoursProjection getOpeningHours() {
        return openingHours;
    }

    public String getPictureUrls() {
        return pictureUrls;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() {
        return owner;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public boolean isOpenNow() {
        if (openingHours == null || openingHours.hoursPerDay() == null) return false;

        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        LocalTime now = LocalTime.now();

        var todayRange = openingHours.getForDay(day);
        if (todayRange == null) return false;

        return now.isAfter(todayRange.open()) && now.isBefore(todayRange.close());
    }

    public boolean matches(String cuisineFilter, String priceRangeFilter) {
        boolean cuisineMatches = cuisineFilter == null || cuisineFilter.isBlank()
                || typeOfCuisine.equalsIgnoreCase(cuisineFilter);
        boolean priceMatches = priceRangeFilter == null || priceRangeFilter.isBlank()
                || priceRange.equalsIgnoreCase(priceRangeFilter);
        return cuisineMatches && priceMatches;
    }

}
