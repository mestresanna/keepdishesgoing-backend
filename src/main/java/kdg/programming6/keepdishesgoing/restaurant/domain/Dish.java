package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.common.events.DishPublishedEvent;
import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public static Dish create(DishId id, String name,RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, Float price, String pictureUrl, DishState state){
        return new Dish(id, name, restaurantId, type, tags, description, price, pictureUrl, state);
    }

    public DishId getId() {
        return id;
    }

    public void setId(DishId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public Set<FoodTag> getTags() {
        return tags;
    }

    public void setTags(Set<FoodTag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public DishState getState() {
        return state;
    }

    public void setState(DishState state) {
        this.state = state;
    }

    public boolean isHasPendingChanges() {
        return hasPendingChanges;
    }

    public void setHasPendingChanges(boolean hasPendingChanges) {
        this.hasPendingChanges = hasPendingChanges;
    }

    public PublishDishesSchedule getScheduledChange() {
        return scheduledChange;
    }

    public void setScheduledChange(PublishDishesSchedule scheduledChange) {
        this.scheduledChange = scheduledChange;
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
        if (this.state == DishState.PUBLISHED) {
            throw new IllegalStateException("Dish is already published.");
        }

        if (this.state == DishState.DRAFT || this.state == DishState.UNPUBLISHED) {
            this.state = DishState.PUBLISHED;
            this.hasPendingChanges = false;
            this.scheduledChange = null;

            recordEvent(new DishPublishedEvent(this.id.dishId()));
        } else {
            throw new IllegalStateException("Dish must be DRAFT or UNPUBLISHED to publish.");
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
    }

    public void markBackInStock() {
        if (state != DishState.OUT_OF_STOCK) throw new IllegalStateException("Dish must be out of stock.");
        this.state = DishState.PUBLISHED;
    }

    public boolean hasPendingChanges(){
        return this.hasPendingChanges;
    }
    public void scheduleChange(PublishDishesSchedule  schedule){
        //TODO
        //change schedules, check method later
        this.scheduledChange = schedule;
    }
    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

}
