package kdg.programming6.keepdishesgoing.order.adapter.out.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "orders")
public class OrderJpaEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "basket_id", nullable = false)
    private UUID basketId; // reference to original basket

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId; // snapshot from basket

    // Snapshot of basket items at order time
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();

    // Customer embedded
    @Embedded
    private CustomerEmbeddable customer;

    @Column(name = "message")
    private String message;

    @Column(name = "total_price", precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public OrderJpaEntity() {}

    // getters & setters
    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public UUID getBasketId() { return basketId; }
    public void setBasketId(UUID basketId) { this.basketId = basketId; }

    public UUID getRestaurantId() { return restaurantId; }
    public void setRestaurantId(UUID restaurantId) { this.restaurantId = restaurantId; }

    public List<OrderItemJpaEntity> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemJpaEntity> orderItems) {
        this.orderItems.clear();
        if (orderItems != null) {
            orderItems.forEach(this::addOrderItem);
        }
    }

    public void addOrderItem(OrderItemJpaEntity item) {
        item.setOrder(this);
        this.orderItems.add(item);
    }

    public void removeOrderItem(OrderItemJpaEntity item) {
        this.orderItems.remove(item);
        item.setOrder(null);
    }

    public CustomerEmbeddable getCustomer() { return customer; }
    public void setCustomer(CustomerEmbeddable customer) { this.customer = customer; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
