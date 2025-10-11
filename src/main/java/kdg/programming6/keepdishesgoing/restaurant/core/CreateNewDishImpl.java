package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishId;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.DishPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateNewDishImpl implements CreateDishUseCase {
    private final DishPort dishPort;

    public CreateNewDishImpl(DishPort dishPort) {
        this.dishPort = dishPort;
    }

    @Override
    public void handle(CreateDishCommand command) {
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

        dish.setId(new DishId(UUID.randomUUID()));
        dishPort.save(dish);
    }
}
