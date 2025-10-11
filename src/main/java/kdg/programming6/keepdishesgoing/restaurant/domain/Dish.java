package kdg.programming6.keepdishesgoing.restaurant.domain;

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

    public Dish(String name,RestaurantId restaurantId, DishType type, Set<FoodTag> tags, String description, Float price, String pictureUrl, DishState state) {
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
        this.state = DishState.PUBLISHED;
        this.hasPendingChanges = false;
        this.scheduledChange = null;
    }

    public void unpublish() {
        this.state = DishState.UNPUBLISHED;
        this.hasPendingChanges = false;
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
}
