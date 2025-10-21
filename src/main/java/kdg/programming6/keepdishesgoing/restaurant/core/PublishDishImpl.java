package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.events.DishPublishedEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
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
    private final List<SaveDishPort> saveDishPort;
    private final LoadMenuPort loadMenuPort;
    private final List<SaveMenuPort> saveMenuPort;
    private final LoadRestaurantPort loadRestaurantPort;
    private final List<SaveRestaurantPort> saveRestaurantPort;
    private static final Logger logger = LoggerFactory.getLogger(PublishDishImpl.class);


    public PublishDishImpl(LoadDishPort loadDishPort,
                           List<SaveDishPort>  saveDishPort,
                           LoadMenuPort loadMenuPort,
                           List<SaveMenuPort> saveMenuPort, LoadRestaurantPort loadRestaurantPort, List<SaveRestaurantPort> saveRestaurantPort) {
        this.loadDishPort = loadDishPort;
        this.saveDishPort = saveDishPort;
        this.loadMenuPort = loadMenuPort;
        this.saveMenuPort = saveMenuPort;
        this.loadRestaurantPort = loadRestaurantPort;
        this.saveRestaurantPort = saveRestaurantPort;
    }

    @Override
    @Transactional
    public void publishDish(UUID dishId) {
        Dish dish = loadDishPort.loadById(dishId).orElseThrow(() -> new DishNotFoundException("Dish not found: " + dishId));
        Menu menu = loadMenuPort.loadMenuByRestaurantId(dish.getRestaurantId())
                .orElseGet(() -> Menu.create(dish.getRestaurantId()));
        Restaurant restaurant =  loadRestaurantPort.loadById(dish.getRestaurantId().restaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + dish.getRestaurantId()));
        dish.publish();
        dish.recordEvent(new DishPublishedEvent(dish.getId().dishId(), menu.getId(), dish.getName(), dish.getRestaurantId().restaurantId(), dish.getType().toString(), dish.getTags(),dish.getDescription(), dish.getPrice(), dish.getPictureUrl(), dish.getState().toString()));
        menu.addDish(dish);
        menu.updateAvgPrice();
        saveMenuPort.forEach(port -> port.save(menu));
        saveDishPort.forEach(port-> port.save(dish));
        restaurant.setPriceRange(menu.getAvgPrice());
        saveRestaurantPort.forEach(port -> port.save(restaurant));
    }
}