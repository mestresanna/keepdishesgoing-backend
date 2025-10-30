package kdg.programming6.keepdishesgoing.restaurant.core.dish;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.FindAllDishesUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FindAllDishesImpl implements FindAllDishesUseCase {
    private final LoadDishPort loadDishPort;

    public FindAllDishesImpl(LoadDishPort loadDishPort) {
        this.loadDishPort = loadDishPort;
    }

    @Override
    public List<Dish> findAllDishesByRestaurant(UUID restaurantId) {
        return loadDishPort.loadAllDishesByRestaurant(restaurantId);
    }
}
