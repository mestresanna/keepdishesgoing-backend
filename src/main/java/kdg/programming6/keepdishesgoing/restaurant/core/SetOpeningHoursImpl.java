package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.OwnerNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.SetOpeningHoursCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.SetOpeningHoursUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class SetOpeningHoursImpl implements SetOpeningHoursUseCase {
    private final LoadRestaurantPort repository;
    private final List<SaveRestaurantPort> saveRestaurantPort;

    public SetOpeningHoursImpl(LoadRestaurantPort repository, List<SaveRestaurantPort> saveRestaurantPort) {
        this.repository = repository;
        this.saveRestaurantPort = saveRestaurantPort;
    }

    @Override
    @Transactional
    public Restaurant setOpeningHours(SetOpeningHoursCommand command) throws RestaurantNotFoundException, OwnerNotFoundException {
        Restaurant restaurant = repository.loadById(command.restaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + command.restaurantId()));

        if (!restaurant.getOwner().ownerId().equals(command.ownerId())) {
            throw new OwnerNotFoundException("You are not the owner of this restaurant.");
        }

        restaurant.changeOpeningHours(command.openingHours());
        saveRestaurantPort.forEach(port -> port.save(restaurant));
        return restaurant;
    }
}
