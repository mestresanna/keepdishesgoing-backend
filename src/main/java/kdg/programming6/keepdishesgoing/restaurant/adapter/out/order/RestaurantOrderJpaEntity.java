package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant_orders", schema = "restaurant")
public class RestaurantOrderJpaEntity {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false)
    private UUID orderId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "delivery_street", nullable = false)
    private String deliveryStreet;

    @Column(name = "delivery_number", nullable = false)
    private Integer deliveryNumber;

    @Column(name = "delivery_city", nullable = false)
    private String deliveryCity;

    @Column(name = "delivery_postal_code", nullable = false)
    private String deliveryPostalCode;

    @Column(name = "delivery_country", nullable = false)
    private String deliveryCountry;

    // Use mappedBy for bidirectional relationship
    @OneToMany(mappedBy = "restaurantOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RestaurantOrderItemJpaEntity> items = new ArrayList<>();

    @Column(name = "total_price", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public RestaurantOrderJpaEntity() {}

    public void addItem(RestaurantOrderItemJpaEntity item) {
        items.add(item);
        item.setRestaurantOrder(this);
    }

    public void removeItem(RestaurantOrderItemJpaEntity item) {
        items.remove(item);
        item.setRestaurantOrder(null);
    }
    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public UUID getRestaurantId() { return restaurantId; }
    public void setRestaurantId(UUID restaurantId) { this.restaurantId = restaurantId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getDeliveryStreet() { return deliveryStreet; }
    public void setDeliveryStreet(String deliveryStreet) { this.deliveryStreet = deliveryStreet; }

    public Integer getDeliveryNumber() { return deliveryNumber; }
    public void setDeliveryNumber(Integer deliveryNumber) { this.deliveryNumber = deliveryNumber; }

    public String getDeliveryCity() { return deliveryCity; }
    public void setDeliveryCity(String deliveryCity) { this.deliveryCity = deliveryCity; }

    public String getDeliveryPostalCode() { return deliveryPostalCode; }
    public void setDeliveryPostalCode(String deliveryPostalCode) { this.deliveryPostalCode = deliveryPostalCode; }

    public String getDeliveryCountry() { return deliveryCountry; }
    public void setDeliveryCountry(String deliveryCountry) { this.deliveryCountry = deliveryCountry; }

    public List<RestaurantOrderItemJpaEntity> getItems() { return items; }
    public void setItems(List<RestaurantOrderItemJpaEntity> items) {
        this.items.clear();
        if (items != null) {
            items.forEach(this::addItem);
        }
    }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}