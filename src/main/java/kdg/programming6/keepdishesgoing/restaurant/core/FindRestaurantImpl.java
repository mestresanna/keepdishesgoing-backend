package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.FindRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindRestaurantImpl implements FindRestaurantUseCase {
    private final RestaurantPort repository;
    public FindRestaurantImpl(RestaurantPort repository) {
        this.repository = repository;}

    @Override
    public Restaurant findRestaurant(UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
