package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishId;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveDishPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateNewDishImpl implements CreateDishUseCase {
    private final SaveDishPort saveDishPort;

    public CreateNewDishImpl(SaveDishPort saveDishPort) {
        this.saveDishPort = saveDishPort;
    }

    @Override
    public void handle(CreateDishCommand command) {
        Dish dish = Dish.create(
                command.dishId(),
                command.name(),
                command.restaurantId(),
                command.type(),
                command.tags(),
                command.description(),
                command.price(),
                command.pictureUrl(),
                command.state()
        );

        dish.setId(new DishId(UUID.randomUUID()));
        saveDishPort.save(dish);
    }
}
