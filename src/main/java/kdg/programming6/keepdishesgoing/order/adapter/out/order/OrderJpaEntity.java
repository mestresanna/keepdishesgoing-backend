package kdg.programming6.keepdishesgoing.order.adapter.out.order;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "orders")
public class OrderJpaEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "basket_id", nullable = false)
    private UUID basketId; // snapshot of basket used for this order

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