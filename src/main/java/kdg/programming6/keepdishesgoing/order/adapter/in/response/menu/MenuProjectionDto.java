package kdg.programming6.keepdishesgoing.order.adapter.in.response.menu;

import kdg.programming6.keepdishesgoing.order.adapter.in.response.dish.DishProjectionDto;

import java.util.List;
import java.util.UUID;

public record MenuProjectionDto (
        UUID menuId,
        List<DishProjectionDto> dishes
){
}
