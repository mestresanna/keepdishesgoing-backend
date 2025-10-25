package kdg.programming6.keepdishesgoing.order.adapter.out.basket;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "baskets", schema = "orders")
public class BasketJpaEntity {
    @Id
    @Column(name = "basket_id", nullable = false)
    private UUID basketId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BasketItemJpaEntity> items = new ArrayList<>();

    @Column(name = "total_price", precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "message")
    private String message;

    public BasketJpaEntity() {}

    // getters & setters
    public UUID getBasketId() { return basketId; }
    public void setBasketId(UUID basketId) { this.basketId = basketId; }

    public UUID getRestaurantId() { return restaurantId; }
    public void setRestaurantId(UUID restaurantId) { this.restaurantId = restaurantId; }

    public List<BasketItemJpaEntity> getItems() { return items; }
    public void setItems(List<BasketItemJpaEntity> items) {
        this.items.clear();
        if (items != null) {
            items.forEach(this::addItem);
        }
    }

    public void addItem(BasketItemJpaEntity item) {
        item.setBasket(this);
        this.items.add(item);
    }

    public void removeItem(BasketItemJpaEntity item) {
        this.items.remove(item);
        item.setBasket(null);
    }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
