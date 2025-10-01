package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class CreateNewRestaurantImpl implements CreateRestaurantUseCase {

    private final RestaurantPort repository;

    public CreateNewRestaurantImpl(RestaurantPort repository) {
        this.repository = repository;
    }

    @Override
    public void handle(CreateRestaurantCommand command) {
        Restaurant restaurant = Restaurant.create(

                command.name(),
                command.address(),
                command.contactEmail(),
                String.valueOf(command.pictureUrls()),
                command.defaultPreparationTimeInMinutes(),
                command.typeOfCuisine(),
                command.openingHours(),
                command.owner()
        );
        repository.save(restaurant);
    }
}
