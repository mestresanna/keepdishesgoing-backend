package kdg.programming6.keepdishesgoing.restaurant.core.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.FindRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.UUID;

@Service
@Transactional
public class FindRestaurantImpl implements FindRestaurantUseCase {
    private final LoadRestaurantPort repository;
    public FindRestaurantImpl(LoadRestaurantPort repository) {
        this.repository = repository;}

    @Override
    public Restaurant findRestaurant(UUID id) {
        return repository.loadById(id).orElseThrow();
    }
}
