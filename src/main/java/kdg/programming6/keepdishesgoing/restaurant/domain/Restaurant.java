package kdg.programming6.keepdishesgoing.restaurant.domain;
import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.events.RestaurantCreatedEvent;

import java.util.ArrayList;
import java.util.List;
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
        return restaurant;
    }
        public static Restaurant create(String name, Address address, String contactEmail,
                String pictureUrls, int defaultPreparationTimeInMinutes,
        TypeOfCuisine typeOfCuisine, OpeningHours openingHours, OwnerId owner, PriceRange priceRange) {
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
        restaurant.recordEvent(new RestaurantCreatedEvent(restaurant.restaurantId.restaurantId()));
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

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

}


