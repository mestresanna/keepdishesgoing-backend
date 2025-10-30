package kdg.programming6.keepdishesgoing.order.adapter.in.response.menu;

import kdg.programming6.keepdishesgoing.order.adapter.in.response.dish.DishProjectionMapperDto;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuProjectionMapperDto {

    private final DishProjectionMapperDto dishMapper;

    public MenuProjectionMapperDto(DishProjectionMapperDto dishMapper) {
        this.dishMapper = dishMapper;
    }

    public MenuProjectionDto toDtoWithDishes(MenuProjection menu, List<DishProjection> dishes) {
        var dishDtos = dishes.stream()
                .map(dishMapper::toDto)
                .toList();

        return new MenuProjectionDto(menu.getId(), dishDtos);
    }

    public MenuProjectionDto toDto(MenuProjection menu) {
        var dishDtos = menu.getDishes().stream()
                .map(dishMapper::toDto)
                .toList();

        return new MenuProjectionDto(menu.getId(), dishDtos);
    }
}
