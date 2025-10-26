package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.EditDishCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.EditDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadDishPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveDishPort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class EditDishImpl implements EditDishUseCase {
    private final LoadDishPort loadDishPort;
    private final List<SaveDishPort> saveDishPort;

    public EditDishImpl(LoadDishPort loadDishPort, List<SaveDishPort>  saveDishPort) {
        this.loadDishPort = loadDishPort;
        this.saveDishPort = saveDishPort;
    }

    @Override
    public void editDish(EditDishCommand command) {
        Dish dish = loadDishPort.loadById(command.dishId())
                .orElseThrow(() -> new DishNotFoundException("Dish not found: " + command.dishId()));

        dish.editAsDraft(command.name(), command.description(), command.price(), command.pictureUrl(), command.type(),  command.tags());

        saveDishPort.forEach(port-> port.save(dish));
    }
}
