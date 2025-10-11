package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.PublishDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadDishPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadMenuPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveDishPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveMenuPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublishDishImpl implements PublishDishUseCase {
    private final LoadDishPort loadDishPort;
    private final SaveDishPort saveDishPort;
    private final LoadMenuPort loadMenuPort;
    private final SaveMenuPort saveMenuPort;

    public PublishDishImpl(LoadDishPort loadDishPort,
                              SaveDishPort saveDishPort,
                              LoadMenuPort loadMenuPort,
                              SaveMenuPort saveMenuPort) {
        this.loadDishPort = loadDishPort;
        this.saveDishPort = saveDishPort;
        this.loadMenuPort = loadMenuPort;
        this.saveMenuPort = saveMenuPort;
    }

    @Override
    @Transactional
    public void handle(UUID dishId) {
        Dish dish = loadDishPort.loadById(dishId).orElseThrow(() -> new DishNotFoundException("Dish not found: " + dishId));
        Menu menu = loadMenuPort.loadMenuByRestaurantId(dish.getRestaurantId())
                .orElseGet(() -> new Menu(UUID.randomUUID(), dish.getRestaurantId()));
        dish.publish();
        saveDishPort.save(dish);
        menu.addDish(dish);
        saveMenuPort.save(menu);
    }
}
