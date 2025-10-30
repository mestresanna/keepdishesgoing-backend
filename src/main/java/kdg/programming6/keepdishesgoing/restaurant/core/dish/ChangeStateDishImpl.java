package kdg.programming6.keepdishesgoing.restaurant.core.dish;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.events.dish.DishStateChangedEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.ChangeStateDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChangeStateDishImpl implements ChangeStateDishUseCase {
    private final List<SaveDishPort> saveDishPort;
    private final LoadDishPort loadDishPort;

    public ChangeStateDishImpl(List<SaveDishPort> saveDishPort, LoadDishPort loadDishPort) {
        this.saveDishPort = saveDishPort;
        this.loadDishPort = loadDishPort;
    }

    @Override
    @Transactional
    public void changeStateDish(UUID dishId) {
        Dish dish = loadDishPort.loadById(dishId).orElseThrow(() -> new DishNotFoundException("Dish not found: " +  dishId));
        dish.changeStateDish();
        dish.recordEvent(new DishStateChangedEvent(dishId, dish.getState().toString()));
        saveDishPort.forEach(port -> port.save(dish));
    }
}
