package kdg.programming6.keepdishesgoing.restaurant.core;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.FindAllRestaurantsUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadAllRestaurantsPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FindAllRestaurantsImpl implements FindAllRestaurantsUseCase {
    private final LoadAllRestaurantsPort repository;
    public FindAllRestaurantsImpl(LoadAllRestaurantsPort repository) {
        this.repository = repository;}


    @Override
    public List<Restaurant> findAllByOwner(UUID ownerId) {
        return repository.loadAllByOwner(ownerId);
    }
}
