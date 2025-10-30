package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.dish;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;

import java.util.stream.Collectors;

public class DishMapperDto {
    public static DishDto toDto(Dish dish) {
        return new DishDto(
                dish.getId().dishId().toString(),
                dish.getName(),
                dish.getDescription(),
                dish.getType().toString(),
                dish.getPrice(),
                dish.getPictureUrl(),
                dish.getType().toString(), // or dish.getType().name(), depending on enum
                dish.getTags().stream().map(FoodTag::tag).collect(Collectors.toSet()),
                dish.getState().toString()
        );
    }
}
