package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.OwnerNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.SetOpeningHoursCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.SetOpeningHoursUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetOpeningHoursImpl implements SetOpeningHoursUseCase {
    private final LoadRestaurantPort repository;
    private final List<RestaurantPort> restaurantPort;

    public SetOpeningHoursImpl(LoadRestaurantPort repository, List<RestaurantPort> restaurantPort) {
        this.repository = repository;
        this.restaurantPort = restaurantPort;
    }

    @Override
    public Restaurant setOpeningHours(SetOpeningHoursCommand command) throws RestaurantNotFoundException, OwnerNotFoundException {
        Restaurant restaurant = repository.loadById(command.restaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + command.restaurantId()));

        if (!restaurant.getOwner().ownerId().equals(command.ownerId())) {
            throw new OwnerNotFoundException("You are not the owner of this restaurant.");
        }

        restaurant.changeOpeningHours(command.openingHours());
        restaurantPort.forEach(port -> port.save(restaurant));
        return restaurant;
    }
}
