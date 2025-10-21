package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.events.DishUnpublishedEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.UnpublishDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UnpublishDishImpl implements UnpublishDishUseCase {
    private final LoadDishPort loadDishPort;
    private final List<SaveDishPort> saveDishPort;
    private final LoadMenuPort loadMenuPort;
    private final List<SaveMenuPort> saveMenuPort;
    private final LoadRestaurantPort loadRestaurantPort;
    private final List<SaveRestaurantPort> saveRestaurantPort;

    private static final Logger logger = LoggerFactory.getLogger(UnpublishDishImpl.class);


    public UnpublishDishImpl(LoadDishPort loadDishPort, List<SaveDishPort>  saveDishPort, LoadMenuPort loadMenuPort, List<SaveMenuPort> saveMenuPort, LoadRestaurantPort loadRestaurantPort, List<SaveRestaurantPort> saveRestaurantPort) {
        this.loadDishPort = loadDishPort;
        this.saveDishPort = saveDishPort;
        this.loadMenuPort = loadMenuPort;
        this.saveMenuPort = saveMenuPort;
        this.loadRestaurantPort = loadRestaurantPort;
        this.saveRestaurantPort = saveRestaurantPort;
    }


    @Override
    @Transactional
    public void unplublishDish(UUID dishId) {
        Dish dish =loadDishPort.loadById(dishId).orElseThrow(() -> new DishNotFoundException("Dish with id " + dishId + " not found"));
        Menu menu = loadMenuPort.loadMenuByRestaurantId(dish.getRestaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Menu with id " + dish.getRestaurantId() + " not found"));
        Restaurant restaurant =  loadRestaurantPort.loadById(dish.getRestaurantId().restaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + dish.getRestaurantId()));
        dish.unpublish();
        dish.recordEvent(new DishUnpublishedEvent(dish.getId().dishId(), menu.getId()));
        saveDishPort.forEach(port-> port.save(dish));
        menu.removeDish(dish);
        saveMenuPort.forEach(port-> port.save(menu));
        restaurant.setPriceRange(menu.updateAvgPrice());
        saveRestaurantPort.forEach(port -> port.save(restaurant));
    }
}
