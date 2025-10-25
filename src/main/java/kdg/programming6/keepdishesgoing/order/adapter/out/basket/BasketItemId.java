package kdg.programming6.keepdishesgoing.order.adapter.out.basket;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class BasketItemId implements Serializable {

    @Column(name = "basket_id")
    private UUID basketId;

    @Column(name = "dish_id")
    private UUID dishId;

    protected BasketItemId() {}

    public BasketItemId(UUID basketId, UUID dishId) {
        this.basketId = basketId;
        this.dishId = dishId;
    }

    public UUID getBasketId() { return basketId; }
    public UUID getDishId() { return dishId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketItemId that)) return false;
        return Objects.equals(basketId, that.basketId) &&
                Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, dishId);
    }
}
