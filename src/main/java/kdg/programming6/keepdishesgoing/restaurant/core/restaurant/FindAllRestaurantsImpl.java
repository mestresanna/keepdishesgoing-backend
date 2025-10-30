package kdg.programming6.keepdishesgoing.restaurant.core.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.FindAllRestaurantsUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant.LoadAllRestaurantsPort;
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
