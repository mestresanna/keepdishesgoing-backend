package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
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
    private final SaveDishPort saveDishPort;
    private final LoadMenuPort loadMenuPort;
    private final SaveMenuPort saveMenuPort;
    private final List<UpdateMenuPort> updateMenuPorts;

    private static final Logger logger = LoggerFactory.getLogger(UnpublishDishImpl.class);


    public UnpublishDishImpl(LoadDishPort loadDishPort, SaveDishPort saveDishPort, LoadMenuPort loadMenuPort, SaveMenuPort saveMenuPort, List<UpdateMenuPort> updateMenuPorts) {
        this.loadDishPort = loadDishPort;
        this.saveDishPort = saveDishPort;
        this.loadMenuPort = loadMenuPort;
        this.saveMenuPort = saveMenuPort;
        this.updateMenuPorts = updateMenuPorts;
    }


    @Override
    @Transactional
    public void handle(UUID dishId) {
        Dish dish =loadDishPort.loadById(dishId).orElseThrow(() -> new DishNotFoundException("Dish with id " + dishId + " not found"));
        Menu menu = loadMenuPort.loadMenuByRestaurantId(dish.getRestaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Menu with id " + dish.getRestaurantId() + " not found"));
        dish.unpublish();
        saveDishPort.save(dish);
        menu.removeDish(dish);
        saveMenuPort.save(menu);
        logger.info("Uncommitted events: {}", menu.getUncommittedEvents());
        updateMenuPorts.forEach(port -> port.update(menu));
        menu.commitEvents();
    }
}
