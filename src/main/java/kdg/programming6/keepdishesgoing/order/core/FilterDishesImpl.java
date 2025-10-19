package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.in.dish.FilterDishesUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.LoadDishProjectionPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FilterDishesImpl implements FilterDishesUseCase {
    private final LoadDishProjectionPort loadDishProjectionPort;
    public FilterDishesImpl(LoadDishProjectionPort loadDishProjectionPort) {
        this.loadDishProjectionPort = loadDishProjectionPort;
    }

    @Override
    @Transactional
    public List<DishProjection> filterDishes(UUID menuId, String type, Set<String> tags) {
        List<DishProjection> dishes = loadDishProjectionPort.loadAllDishProjections(menuId);
        return dishes.stream()
                .filter(dish -> dish.matches(type, tags))
                .toList();
    }
}
