package kdg.programming6.keepdishesgoing.order.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MapsId;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class DishProjectionId implements Serializable {

    private UUID menuId;
    @Column(name = "dish_id")
    @MapsId("dishId")
    private UUID dishId;

    protected DishProjectionId() {}

    public DishProjectionId(UUID menuId, UUID dishId) {
        this.menuId = menuId;
        this.dishId = dishId;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishProjectionId that)) return false;
        return Objects.equals(menuId, that.menuId) &&
                Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, dishId);
    }
}
