package kdg.programming6.keepdishesgoing.order.domain.projections;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishState;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishType;

import java.util.Set;
import java.util.UUID;

public class DishProjection {
    UUID dishId;
    String name;
    UUID restaurantId;
    String type;
    Set<FoodTag> tags;
    String description;
    Float price;
    String pictureUrl;
    StatusDish status;

    public DishProjection(UUID dishId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, Float price, String pictureUrl, StatusDish status) {
        this.dishId = dishId;
        this.name = name;
        this.restaurantId = restaurantId;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.status = status;
    }

    public StatusDish getStatus() {
        return status;
    }

    public UUID getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String getType() {
        return type;
    }

    public Set<FoodTag> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void changeStateDish() {
   if  (status == StatusDish.OUT_OF_STOCK) {
            markBackInStock();
        } if  (status == StatusDish.PUBLISHED) {
            markOutOfStock();
        }
    }

    public void markOutOfStock() {
        if (status != StatusDish.PUBLISHED) throw new IllegalStateException("Only published dishes can go out of stock.");
        this.status = StatusDish.OUT_OF_STOCK;
    }

    public void markBackInStock() {
        if (status != StatusDish.OUT_OF_STOCK) throw new IllegalStateException("Dish must be out of stock.");
        this.status = StatusDish.PUBLISHED;
    }

}
