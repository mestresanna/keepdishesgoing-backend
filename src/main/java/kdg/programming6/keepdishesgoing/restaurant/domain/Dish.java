package kdg.programming6.keepdishesgoing.restaurant.domain;
import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public class Dish {
    private DishId id;
    private String name;
    private RestaurantId restaurantId;
    private DishType type;
    private Set<FoodTag> tags;
    private String description;
    private BigDecimal price;
    private String pictureUrl;
    private DishState state;
    private final List<DomainEvent> domainEvents = new ArrayList<>();
    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();

    public Dish(DishId id, String name, RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, BigDecimal price, String pictureUrl, DishState state) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.state = state;
    }

    public static Dish create(String name, RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, BigDecimal price, String pictureUrl, DishState state){
        return new Dish(new DishId(UUID.randomUUID()), name, restaurantId, type, tags, description, price, pictureUrl, state);
    }
    public static Dish rehydrate(DishId dishId, String name, RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, BigDecimal price, String pictureUrl, DishState state){
        return new Dish(dishId, name, restaurantId, type, tags, description, price, pictureUrl, state);
    }

    public DishId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DishType getType() {
        return type;
    }

    public Set<FoodTag> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public DishState getState() {
        return state;
    }

    public void publish() {
        switch (this.state) {
            case DRAFT -> {
                this.state = DishState.PUBLISHED;
            }
            case PUBLISHED -> {
                    throw new IllegalStateException("Dish is already published and has no pending changes.");
            }
            case UNPUBLISHED -> {
                this.state = DishState.PUBLISHED;
            }
            case OUT_OF_STOCK -> {
                throw new IllegalStateException("Cannot publish dish that is out of stock.");
            }
            default -> throw new IllegalStateException("Unsupported dish state: " + state);
        }
    }

    public void unpublish() {
        if (state != DishState.PUBLISHED)
            throw new IllegalStateException("Only published dishes can be unpublished");
        this.state = DishState.UNPUBLISHED;
    }

    public void changeStateDish() {
        if (this.state == DishState.UNPUBLISHED || this.state == DishState.DRAFT) {
            throw new IllegalStateException("Dish is not published, cannot change state to out of stock.");
        } if  (this.state == DishState.OUT_OF_STOCK) {
            markBackInStock();
        } else if  (this.state == DishState.PUBLISHED) {
            markOutOfStock();
        }
    }

    public void markOutOfStock() {
        if (state != DishState.PUBLISHED) throw new IllegalStateException("Only published dishes can go out of stock.");
        this.state = DishState.OUT_OF_STOCK;
    }

    public void markBackInStock() {
        if (state != DishState.OUT_OF_STOCK) throw new IllegalStateException("Dish must be out of stock.");
        this.state = DishState.PUBLISHED;
    }

    public void editAsDraft(String newName, String newDescription, BigDecimal newPrice, String newPictureUrl, DishType newType, Set<FoodTag> newtags) {
        if (state == DishState.PUBLISHED) {
            this.name = newName;
            this.description = newDescription;
            this.price = newPrice;
            this.state = DishState.DRAFT;
            this.tags = newtags;
            this.pictureUrl = newPictureUrl;
            this.type = newType;
        } else if (state == DishState.DRAFT) {
            this.name = newName;
            this.description = newDescription;
            this.price = newPrice;
            this.tags = newtags;
            this.pictureUrl = newPictureUrl;
            this.type = newType;
        } else {
            throw new IllegalStateException("Cannot edit dish in state: " + state);
        }
    }

    public void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return new ArrayList<>(Stream.concat(domainEvents.stream(), uncommittedEvents.stream()).toList());
    }

}
