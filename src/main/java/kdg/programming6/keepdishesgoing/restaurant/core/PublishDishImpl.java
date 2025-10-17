package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.MenuNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.PublishDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PublishDishImpl implements PublishDishUseCase {
    private final LoadDishPort loadDishPort;
    private final SaveDishPort saveDishPort;
    private final LoadMenuPort loadMenuPort;
    private final SaveMenuPort saveMenuPort;
    private final LoadRestaurantPort loadRestaurantPort;
    private final List<RestaurantPort> restaurantPort;
    private static final Logger logger = LoggerFactory.getLogger(PublishDishImpl.class);


    public PublishDishImpl(LoadDishPort loadDishPort,
                           SaveDishPort saveDishPort,
                           LoadMenuPort loadMenuPort,
                           SaveMenuPort saveMenuPort, LoadRestaurantPort loadRestaurantPort, List<RestaurantPort> restaurantPort) {
        this.loadDishPort = loadDishPort;
        this.saveDishPort = saveDishPort;
        this.loadMenuPort = loadMenuPort;
        this.saveMenuPort = saveMenuPort;
        this.loadRestaurantPort = loadRestaurantPort;
        this.restaurantPort = restaurantPort;
    }

    @Override
    @Transactional
    public void publishDish(UUID dishId) {
        Dish dish = loadDishPort.loadById(dishId).orElseThrow(() -> new DishNotFoundException("Dish not found: " + dishId));
        Menu menu = loadMenuPort.loadMenuByRestaurantId(dish.getRestaurantId())
                .orElseGet(() -> Menu.create(dish.getRestaurantId()));
        Restaurant restaurant =  loadRestaurantPort.loadById(dish.getRestaurantId().restaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + dish.getRestaurantId()));
        dish.publish();
        saveDishPort.save(dish);
        menu.addDish(dish);
        menu.setAvgPrice();
        saveMenuPort.save(menu);
        logger.info("Uncommitted events: {}", menu.getUncommittedEvents());
        restaurant.setPriceRange(menu.updateAvgPrice());
        restaurantPort.forEach(port -> port.save(restaurant));
        menu.commitEvents();
    }
}