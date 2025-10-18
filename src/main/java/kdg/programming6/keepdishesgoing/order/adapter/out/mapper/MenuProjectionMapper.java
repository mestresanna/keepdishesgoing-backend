package kdg.programming6.keepdishesgoing.order.adapter.out.mapper;

import kdg.programming6.keepdishesgoing.order.adapter.out.dish.DishProjectionJpaEntity;
import kdg.programming6.keepdishesgoing.order.adapter.out.mapper.DishProjectionMapper;
import kdg.programming6.keepdishesgoing.order.adapter.out.menu.MenuProjectionJpaEntity;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuProjectionMapper {


    public static MenuProjection toDomain(MenuProjectionJpaEntity entity) {
        if (entity == null) return null;

        List<DishProjection> dishes = entity.getDishes() != null
                ? entity.getDishes().stream()
                    .map(DishProjectionMapper::toDomain)
                    .collect(Collectors.toList())
                : List.of();

        return new MenuProjection(
                entity.getMenuId(),
                entity.getRestaurantId(),
                dishes,
                entity.getAvgPrice()
        );
    }

    public static MenuProjectionJpaEntity toJpa(MenuProjection domain) {
        if (domain == null) return null;

        MenuProjectionJpaEntity entity = new MenuProjectionJpaEntity(
                domain.getId(),
                domain.getRestaurantId(),
                domain.getAvgPrice()
        );

        if (domain.getDishes() != null && !domain.getDishes().isEmpty()) {
            entity.setDishes(domain.getDishes().stream()
                    .map(dish -> DishProjectionMapper.toJpa(dish, domain.getId(), entity))
                    .collect(Collectors.toList()));
        }

        return entity;
    }
}
