package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "restaurant_order_items", schema = "restaurant")
public class RestaurantOrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id", nullable = false, updatable = false)
    private UUID orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private RestaurantOrderJpaEntity restaurantOrder;

    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "dish_name", nullable = false)
    private String dishName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price_at_order", precision = 12, scale = 2, nullable = false)
    private BigDecimal priceAtOrder;

    public RestaurantOrderItemJpaEntity() {}

    public RestaurantOrderItemJpaEntity(UUID dishId, String dishName, int quantity, BigDecimal priceAtOrder) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    // --- Getters & Setters ---
    public UUID getOrderItemId() { return orderItemId; }

    public RestaurantOrderJpaEntity getRestaurantOrder() { return restaurantOrder; }
    public void setRestaurantOrder(RestaurantOrderJpaEntity restaurantOrder) { this.restaurantOrder = restaurantOrder; }

    public UUID getDishId() { return dishId; }
    public void setDishId(UUID dishId) { this.dishId = dishId; }

    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(BigDecimal priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
