package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.CreateDishCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.CreateDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveDishPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateNewDishImpl implements CreateDishUseCase {
    private final List<SaveDishPort> saveDishPort;

    public CreateNewDishImpl(List<SaveDishPort> saveDishPort) {
        this.saveDishPort = saveDishPort;
    }

    @Override
    @Transactional
    public void createDish(CreateDishCommand command) {
        Dish dish = Dish.create(
                command.name(),
                command.restaurantId(),
                command.type(),
                command.tags(),
                command.description(),
                command.price(),
                command.pictureUrl(),
                command.state()
        );
        saveDishPort.forEach(port -> port.save(dish));
    }
}
