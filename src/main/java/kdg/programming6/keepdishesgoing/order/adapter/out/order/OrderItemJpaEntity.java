package kdg.programming6.keepdishesgoing.order.adapter.out.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items", schema = "orders")
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private UUID orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderJpaEntity order;

    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @Column(name = "name_dish", nullable = false)
    private String nameDish;

    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "price_at_order", precision = 12, scale = 2, nullable = false)
    private BigDecimal priceAtOrder;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    protected OrderItemJpaEntity() {}

    public OrderItemJpaEntity(UUID menuId, UUID dishId, BigDecimal priceAtOrder, Integer quantity, String nameDish) {
        this.menuId = menuId;
        this.dishId = dishId;
        this.priceAtOrder = priceAtOrder;
        this.quantity = quantity;
        this.nameDish = nameDish;
    }

    // Getters & Setters


    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public UUID getOrderItemId() { return orderItemId; }

    public OrderJpaEntity getOrder() { return order; }
    public void setOrder(OrderJpaEntity order) { this.order = order; }

    public UUID getMenuId() { return menuId; }
    public void setMenuId(UUID menuId) { this.menuId = menuId; }

    public UUID getDishId() { return dishId; }
    public void setDishId(UUID dishId) { this.dishId = dishId; }

    public BigDecimal getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(BigDecimal priceAtOrder) { this.priceAtOrder = priceAtOrder; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
