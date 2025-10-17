package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateNewRestaurantImpl implements CreateRestaurantUseCase {

    private final List<RestaurantPort> repository;

    public CreateNewRestaurantImpl(List<RestaurantPort> repository) {
        this.repository = repository;
    }

    @Override
    public void createRestaurant(CreateRestaurantCommand command) {
        Restaurant restaurant = Restaurant.create(
                command.name(),
                command.address(),
                command.contactEmail(),
                String.valueOf(command.pictureUrls()),
                command.defaultPreparationTimeInMinutes(),
                command.typeOfCuisine(),
                command.openingHours(),
                command.owner());
        repository.forEach(port -> port.save(restaurant));
    }
}
