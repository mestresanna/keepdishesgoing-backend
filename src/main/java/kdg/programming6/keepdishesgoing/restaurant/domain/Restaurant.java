package kdg.programming6.keepdishesgoing.restaurant.domain;
import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.events.RestaurantCreatedEvent;
import kdg.programming6.keepdishesgoing.common.events.RestaurantUpdatedEvent;
import kdg.programming6.keepdishesgoing.common.Address;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OwnerId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Restaurant {
    private RestaurantId restaurantId;
    private OwnerId owner;
    private String name;
    private Address address;
    private String pictureUrls;
    private OpeningHours openingHours;
    private TypeOfCuisine typeOfCuisine;
    private String contactEmail;
    private int defaultPreparationTimeInMinutes;
    private PriceRange priceRange;

    private final List<DomainEvent> domainEvents = new ArrayList<>();


    private Restaurant(RestaurantId restaurantId, String name, Address address, String contactEmail,
                       String pictureUrls, int defaultPreparationTimeInMinutes,
                       TypeOfCuisine typeOfCuisine, OpeningHours openingHours, OwnerId owner,  PriceRange priceRange) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.contactEmail = contactEmail;
        this.pictureUrls = pictureUrls;
        this.defaultPreparationTimeInMinutes = defaultPreparationTimeInMinutes;
        this.typeOfCuisine = typeOfCuisine;
        this.openingHours = openingHours;
        this.owner = owner;
        this.priceRange = priceRange;
    }
    public static Restaurant update (RestaurantId restaurantID, String name, Address address, String contactEmail,
                                     String pictureUrls, int defaultPreparationTimeInMinutes,
                                     TypeOfCuisine typeOfCuisine, OpeningHours openingHours, OwnerId owner, PriceRange priceRange) {
        Restaurant restaurant = new Restaurant(
                restaurantID,
                name,
                address,
                contactEmail,
                pictureUrls,
                defaultPreparationTimeInMinutes,
                typeOfCuisine,
                openingHours,
                owner,
                priceRange);
        restaurant.recordEvent(new RestaurantUpdatedEvent(restaurant.restaurantId.restaurantId(), restaurant.owner.ownerId(), restaurant.getName(), restaurant.getAddress().street(), restaurant.getAddress().number(), restaurant.getAddress().city(), restaurant.getAddress().postalCode(), restaurant.getAddress().country(),restaurant.getPictureUrls(), restaurant.getOpeningHours().hoursPerDay(), restaurant.getTypeOfCuisine().toString(), restaurant.getContactEmail(), restaurant.getDefaultPreparationTimeInMinutes(), restaurant.getPriceRange().toString()));

        return restaurant;

    }
    public static Restaurant create(String name, Address address, String contactEmail,
                                    String pictureUrls, int defaultPreparationTimeInMinutes,
                                    TypeOfCuisine typeOfCuisine, OpeningHours openingHours, OwnerId owner) {
        Restaurant restaurant = new Restaurant(
                new RestaurantId(UUID.randomUUID()),
                name,
                address,
                contactEmail,
                pictureUrls,
                defaultPreparationTimeInMinutes,
                typeOfCuisine,
                openingHours,
                owner,
                PriceRange.NONE);
        restaurant.recordEvent(new RestaurantCreatedEvent(restaurant.restaurantId.restaurantId(), restaurant.owner.ownerId(), restaurant.getName(), restaurant.getAddress().street(), restaurant.getAddress().number(), restaurant.getAddress().city(), restaurant.getAddress().postalCode(), restaurant.getAddress().country(),restaurant.getPictureUrls(), openingHours.hoursPerDay() , restaurant.getTypeOfCuisine().toString(), restaurant.getContactEmail(), restaurant.getDefaultPreparationTimeInMinutes(), restaurant.getPriceRange().toString()));
        return restaurant;
    }
    public static Restaurant create(String name, Address address, String contactEmail,
                String pictureUrls, int defaultPreparationTimeInMinutes, TypeOfCuisine typeOfCuisine, OpeningHours openingHours, OwnerId owner, PriceRange priceRange) {
            Restaurant restaurant = new Restaurant(
                    new RestaurantId(UUID.randomUUID()),
                    name,
                    address,
                    contactEmail,
                    pictureUrls,
                    defaultPreparationTimeInMinutes,
                    typeOfCuisine,
                    openingHours,
                    owner,
                    priceRange
            );
        restaurant.recordEvent(new RestaurantCreatedEvent(restaurant.restaurantId.restaurantId(), restaurant.owner.ownerId(), restaurant.getName(), restaurant.getAddress().street(), restaurant.getAddress().number(), restaurant.getAddress().city(), restaurant.getAddress().postalCode(), restaurant.getAddress().country(),restaurant.getPictureUrls(), openingHours.hoursPerDay(), restaurant.getTypeOfCuisine().toString(), restaurant.getContactEmail(), restaurant.getDefaultPreparationTimeInMinutes(), restaurant.getPriceRange().toString()));
                return restaurant;
    }
    public UUID getRestaurantId() { return restaurantId.restaurantId(); }
    public OwnerId getOwner() { return owner; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public String getContactEmail() { return contactEmail; }
    public String getPictureUrls() { return pictureUrls; }
    public int getDefaultPreparationTimeInMinutes() { return defaultPreparationTimeInMinutes; }
    public TypeOfCuisine getTypeOfCuisine() { return typeOfCuisine; }
    public OpeningHours getOpeningHours() { return openingHours; }
    public PriceRange getPriceRange() { return priceRange; }

    public void setPriceRange(BigDecimal averagePrice) {
        if (averagePrice == null) {
            this.priceRange = PriceRange.NONE;
            throw new IllegalArgumentException("Average price is null — there is no price for this restaurant.");
        }

        if (averagePrice.compareTo(BigDecimal.valueOf(10)) <= 0) {
            this.priceRange = PriceRange.CHEAP;
        } else if (averagePrice.compareTo(BigDecimal.valueOf(30)) <= 0) {
            this.priceRange = PriceRange.REGULAR;
        } else if (averagePrice.compareTo(BigDecimal.valueOf(60)) <= 0) {
            this.priceRange = PriceRange.EXPENSIVE;
        } else {
            this.priceRange = PriceRange.PREMIUM;
        }
        recordEvent(new RestaurantUpdatedEvent(restaurantId.restaurantId(), owner.ownerId(), getName(), getAddress().street(), getAddress().number(), getAddress().city(), getAddress().postalCode(), getAddress().country(),getPictureUrls(), openingHours.hoursPerDay(), getTypeOfCuisine().toString(), getContactEmail(), getDefaultPreparationTimeInMinutes(), getPriceRange().toString()));
    }
    public void changeOpeningHours(OpeningHours newOpeningHours) {
        if (newOpeningHours == null || newOpeningHours.hoursPerDay() == null) {
            throw new IllegalArgumentException("Opening hours cannot be null");
        }
        if (Objects.equals(this.openingHours, newOpeningHours)) {
            return;
        }
        this.openingHours = newOpeningHours;
        recordEvent(new RestaurantUpdatedEvent(restaurantId.restaurantId(), owner.ownerId(), getName(), getAddress().street(), getAddress().number(), getAddress().city(),getAddress().postalCode(),  getAddress().country(),getPictureUrls(), openingHours.hoursPerDay(), getTypeOfCuisine().toString(), getContactEmail(), getDefaultPreparationTimeInMinutes(), getPriceRange().toString()));
    }

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

}


