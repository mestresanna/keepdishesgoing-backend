package kdg.programming6.keepdishesgoing.restaurant.domain;
import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Dish {
    private DishId id;
    private String name;
    private RestaurantId restaurantId;
    private DishType type;
    private Set<FoodTag> tags;
    private String description;
    private Float price;
    private String pictureUrl;
    private DishState state;
    private boolean hasPendingChanges;
    private PublishDishesSchedule scheduledChange;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Dish(DishId id, String name,RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, Float price, String pictureUrl, DishState state) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.state = state;
        if (this.state == DishState.DRAFT || this.state == DishState.UNPUBLISHED){
        this.hasPendingChanges = true;} else  {
        this.hasPendingChanges = false;
        }
    }

    public static Dish create(String name,RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, Float price, String pictureUrl, DishState state){
        return new Dish(new DishId(UUID.randomUUID()), name, restaurantId, type, tags, description, price, pictureUrl, state);
    }
    public static Dish rehydrate(DishId dishId, String name, RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, Float price, String pictureUrl, DishState state){
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

    public Float getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public DishState getState() {
        return state;
    }

    public boolean isHasPendingChanges() {
        return hasPendingChanges;
    }

    public PublishDishesSchedule getScheduledChange() {
        return scheduledChange;
    }

    public void editAsDraft(String name, String description, Float price, Set<FoodTag> tags) {
        if (state == DishState.PUBLISHED || state == DishState.OUT_OF_STOCK) {
            this.hasPendingChanges = true;
        }
        this.name = name;
        this.description = description;
        this.price = price;
        this.tags = tags;
    }

    public void publish() {
        switch (this.state) {
            case DRAFT -> {
                this.state = DishState.PUBLISHED;
                this.hasPendingChanges = false;
            }
            case PUBLISHED -> {
                if (this.hasPendingChanges) {
                    this.hasPendingChanges = false;
                } else {
                    throw new IllegalStateException("Dish is already published and has no pending changes.");
                }
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

    public void markOutOfStock() {
        if (state != DishState.PUBLISHED) throw new IllegalStateException("Only published dishes can go out of stock.");
        this.state = DishState.OUT_OF_STOCK;
        //TODO implement the event
    }

    public void markBackInStock() {
        if (state != DishState.OUT_OF_STOCK) throw new IllegalStateException("Dish must be out of stock.");
        this.state = DishState.PUBLISHED;
        //TODO implement the event5
    }

    public void editAsDraft(String newName, String newDescription, Float newPrice, String newPictureUrl, DishType newType, Set<FoodTag> newtags) {
        if (state == DishState.PUBLISHED) {
            this.name = newName;
            this.description = newDescription;
            this.price = newPrice;
            this.hasPendingChanges = true;
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


    public boolean hasPendingChanges(){
        return state == DishState.DRAFT && hasPendingChanges;
    }
    public void scheduleChange(PublishDishesSchedule  schedule){
        //TODO
        //change schedules, check method later
        this.scheduledChange = schedule;
    }
    public void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

}
