package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dishes", schema = "restaurant")
public class DishJpaEntity {
    @Id
    private UUID id;
    private UUID restaurantId;
    private String name;
    private String description;
    private String pictureUrl;
    private String type;
    private BigDecimal price;
    private String state;
    private boolean hasPendingChanges;
    private LocalDateTime scheduledAt;
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FoodTagJpaEntity> foodTags = new ArrayList<>();

    public void addTag(FoodTagJpaEntity tag) {
        foodTags.add(tag);
        tag.setDish(this);
    }

    public UUID getRestaurantId() { return restaurantId; }
    public void setRestaurantId(UUID restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPictureUrl() { return pictureUrl; }
    public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public boolean isHasPendingChanges() { return hasPendingChanges; }
    public void setHasPendingChanges(boolean hasPendingChanges) { this.hasPendingChanges = hasPendingChanges; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public List<FoodTagJpaEntity> getFoodTags() { return foodTags; }
    public void setFoodTags(List<FoodTagJpaEntity> foodTags) { this.foodTags = foodTags; }

}
