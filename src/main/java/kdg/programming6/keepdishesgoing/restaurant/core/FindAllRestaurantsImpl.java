package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.FindAllRestaurantsUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindAllRestaurantsImpl implements FindAllRestaurantsUseCase {
    private final LoadRestaurantPort repository;
    public FindAllRestaurantsImpl(LoadRestaurantPort repository) {
        this.repository = repository;}

    @Override
    public List<Restaurant> findAll() {
        return repository.loadAll();
    }
}
