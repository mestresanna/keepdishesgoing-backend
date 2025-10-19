package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

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


    public MenuProjectionDto toDto(MenuProjection menu) {
        var dishDtos = menu.getDishes().stream()
                .map(dishMapper::toDto)
                .toList();

        return new MenuProjectionDto(dishDtos);
    }
}
