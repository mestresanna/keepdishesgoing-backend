package kdg.programming6.keepdishesgoing.restaurant.adapter.out.menu;

import kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish.DishMapper;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import kdg.programming6.keepdishesgoing.restaurant.port.out.menu.LoadMenuPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.menu.SaveMenuPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MenuJpaAdapter implements LoadMenuPort, SaveMenuPort {
    private final MenuJpaRepository menuRepo;
    private final DishMapper dishMapper;
    public MenuJpaAdapter(MenuJpaRepository menuRepo, DishMapper dishMapper) {
        this.menuRepo = menuRepo;
        this.dishMapper = dishMapper;
    }

    @Override
    public Optional<Menu> loadMenuByRestaurantId(RestaurantId restaurantId) {
        return menuRepo.findByRestaurantId(restaurantId.restaurantId())
                .map(entity -> {
                    var dishes = entity.getMenuDishes().stream()
                            .map(DishesInMenuJpaEntity::getDish)
                            .map(dishMapper::toDomain)
                            .toList();
                    Menu menu = new Menu(entity.getMenuId(),restaurantId);
                    menu.updateDishes(dishes);
                    return menu;
                });
    }

    @Override
    public void save(Menu menu) {
        MenuJpaEntity entity = new MenuJpaEntity();
        entity.setMenuId(menu.getId() != null ? menu.getId() : UUID.randomUUID());
        entity.setRestaurantId(menu.getRestaurantId().restaurantId());

        entity.getMenuDishes().clear();
        for (Dish dish : menu.getDishes()) {
            DishJpaEntity dishEntity = dishMapper.toEntity(dish);
            entity.addDish(dishEntity);
        }
        entity.setAvgPrice(menu.getAvgPrice());
        menuRepo.save(entity);
    }
}
