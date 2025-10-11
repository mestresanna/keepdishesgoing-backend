package kdg.programming6.keepdishesgoing.restaurant.adapter.out.menu;

import jakarta.persistence.*;
import kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;

import java.io.Serializable;

@Entity
@Table(name = "menu_dishes", schema = "restaurant")
public class DishesInMenuJpaEntity implements Serializable {

    @EmbeddedId
    private MenuDishId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")  // vincula amb la part del composite key
    @JoinColumn(name = "menu_id")
    private MenuJpaEntity menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private DishJpaEntity dish;

    public DishesInMenuJpaEntity() {}

    public DishesInMenuJpaEntity(MenuJpaEntity menu, DishJpaEntity dish) {
        this.menu = menu;
        this.dish = dish;
        this.id = new MenuDishId(menu.getMenuId(), dish.getId());
    }

    public MenuDishId getId() { return id; }
    public void setId(MenuDishId id) { this.id = id; }

    public MenuJpaEntity getMenu() { return menu; }
    public void setMenu(MenuJpaEntity menu) { this.menu = menu; }

    public DishJpaEntity getDish() { return dish; }
    public void setDish(DishJpaEntity dish) { this.dish = dish; }

    }
