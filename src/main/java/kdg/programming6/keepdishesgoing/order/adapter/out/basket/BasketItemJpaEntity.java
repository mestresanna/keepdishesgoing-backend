package kdg.programming6.keepdishesgoing.order.adapter.out.basket;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "basket_items", schema = "orders")
public class BasketItemJpaEntity {

    @EmbeddedId
    private BasketItemId id; // (basket_id, dish_id)

    @MapsId("basketId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id", nullable = false)
    private BasketJpaEntity basket;

    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @Column(name = "name_dish", nullable = false)
    private String nameDish;

    @Column(name = "price_at_add", precision = 12, scale = 2, nullable = false)
    private BigDecimal priceAtAdd;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    protected BasketItemJpaEntity() {}

    public BasketItemJpaEntity(UUID basketId, UUID menuId, UUID dishId, BigDecimal priceAtAdd, Integer quantity, String nameDish) {
        this.id = new BasketItemId(basketId, dishId);
        this.menuId = menuId;
        this.priceAtAdd = priceAtAdd;
        this.quantity = quantity;
        this.nameDish = nameDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public BasketItemId getId() { return id; }
    public void setId(BasketItemId id) { this.id = id; }

    public BasketJpaEntity getBasket() { return basket; }
    public void setBasket(BasketJpaEntity basket) { this.basket = basket; }

    public UUID getBasketId() { return id.getBasketId(); }
    public UUID getDishId() { return id.getDishId(); }

    public UUID getMenuId() { return menuId; }
    public void setMenuId(UUID menuId) { this.menuId = menuId; }

    public BigDecimal getPriceAtAdd() { return priceAtAdd; }
    public void setPriceAtAdd(BigDecimal priceAtAdd) { this.priceAtAdd = priceAtAdd; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
