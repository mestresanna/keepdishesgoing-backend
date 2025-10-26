package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.CreateRestaurantCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.CreateRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class CreateNewRestaurantImpl implements CreateRestaurantUseCase {

    private final List<SaveRestaurantPort> repository;

    public CreateNewRestaurantImpl(List<SaveRestaurantPort> repository) {
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
