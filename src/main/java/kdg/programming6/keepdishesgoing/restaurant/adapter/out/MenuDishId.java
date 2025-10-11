package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MenuDishId implements Serializable {

    private UUID menuId;
    private UUID dishId;

    public MenuDishId() {}

    public MenuDishId(UUID menuId, UUID dishId) {
        this.menuId = menuId;
        this.dishId = dishId;
    }

    public UUID getMenuId() { return menuId; }
    public UUID getDishId() { return dishId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDishId that)) return false;
        return Objects.equals(menuId, that.menuId) &&
                Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, dishId);
    }
}
