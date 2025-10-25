package kdg.programming6.keepdishesgoing.order.adapter.out.dish;

import jakarta.persistence.*;
import kdg.programming6.keepdishesgoing.order.adapter.out.menu.MenuProjectionJpaEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dish_projections", schema = "orders")
public class DishProjectionJpaEntity {

    @EmbeddedId
    private DishProjectionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuProjectionJpaEntity menu;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    private String name;
    private String type;
    private String description;
    private BigDecimal price;
    private String pictureUrl;

    private String status;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FoodTagProjectionJpaEntity> foodTags = new ArrayList<>();


    public DishProjectionJpaEntity() {}

    public DishProjectionJpaEntity(DishProjectionId id, MenuProjectionJpaEntity menu, UUID restaurantId, String name, String type, String description, BigDecimal price, String pictureUrl, String status, List<FoodTagProjectionJpaEntity> foodTags) {
        this.id = id;
        this.menu = menu;
        this.restaurantId = restaurantId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.status = status;
        this.foodTags = foodTags;
    }

    // --- Getters ---
    public DishProjectionId getId() { return id; }
    public UUID getMenu() { return id.getMenuId(); }
    public UUID getDishId() { return id.getDishId(); }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public String getPictureUrl() { return pictureUrl; }
    public String getStatus() { return status; }
    public UUID getRestaurantId() {return restaurantId;}
    public List<FoodTagProjectionJpaEntity> getFoodTags() { return foodTags; }

    // ----Setters---

    public void setId(DishProjectionId id) {
        this.id = id;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFoodTags(List<FoodTagProjectionJpaEntity> foodTags) {
        this.foodTags = foodTags;
    }

    public void setMenu(MenuProjectionJpaEntity menu) {
        this.menu = menu;
    }
}
