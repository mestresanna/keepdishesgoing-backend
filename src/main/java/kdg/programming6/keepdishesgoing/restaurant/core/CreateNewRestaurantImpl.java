package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Owner;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
// As an owner, I want to create my restaurant by submitting name, full address, contact email, picture(s) url, default preparation time, type of cuisine, and opening hours.
public class CreateNewRestaurantImpl implements CreateRestaurantUseCase {

    private final RestaurantRepository repository;

    public CreateNewRestaurantImpl(RestaurantRepository repository) {
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
