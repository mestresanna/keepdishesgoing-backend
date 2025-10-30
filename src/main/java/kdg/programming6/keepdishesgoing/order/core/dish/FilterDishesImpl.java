package kdg.programming6.keepdishesgoing.order.core.dish;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.in.dish.FilterDishesUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.dish.LoadDishProjectionPort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    public List<DishProjection> filterDishes(UUID menuId, String type, Set<String> tags, String sortBy, String direction) {
        return loadDishProjectionPort.loadAllDishProjections(menuId).stream()
                .filter(d -> d.matches(type, tags))
                .sorted(getComparator(sortBy, direction))
                .toList();
    }

    private Comparator<DishProjection> getComparator(String sortBy, String direction) {
        Comparator<DishProjection> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(DishProjection::getName, String.CASE_INSENSITIVE_ORDER);
            case "price" -> Comparator.comparing(DishProjection::getPrice);
            default -> Comparator.comparing(DishProjection::getName);
        };

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
