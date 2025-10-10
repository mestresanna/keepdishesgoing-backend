package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.OwnerId;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantId;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.OwnerNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.SetOpeningHoursCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.SetOpeningHoursUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class SetOpeningHoursImpl implements SetOpeningHoursUseCase {
    private final LoadRestaurantPort repository;
    private final RestaurantPort restaurantPort;

    public SetOpeningHoursImpl(LoadRestaurantPort repository, RestaurantPort restaurantPort) {
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
        restaurantPort.save(restaurant);
        return restaurant;
    }
}
