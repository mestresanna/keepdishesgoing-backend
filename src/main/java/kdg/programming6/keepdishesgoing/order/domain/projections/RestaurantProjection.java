package kdg.programming6.keepdishesgoing.order.domain.projections;

import kdg.programming6.keepdishesgoing.common.Address;
import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class RestaurantProjection {
    private RestaurantProjectionId restaurantId;
    private UUID owner;
    private String name;

    private Address address;

    private String pictureUrls;
    private OpeningHoursProjection openingHours;
    private String typeOfCuisine;
    private String contactEmail;
    private int defaultPreparationTimeInMinutes;
    private String priceRange;

    public RestaurantProjection(RestaurantProjectionId restaurantId, UUID owner, String name, Address address, String pictureUrls, OpeningHoursProjection openingHours, String typeOfCuisine, String contactEmail, int defaultPreparationTimeInMinutes, String priceRange) {
        this.restaurantId = restaurantId;
        this.owner = owner;
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() {
        return owner;
    }

    public RestaurantProjectionId getRestaurantId() {
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
