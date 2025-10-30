package kdg.programming6.keepdishesgoing.order.adapter.in.response.dish;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DishProjectionMapperDto {
    public DishProjectionDto toDto(DishProjection dish) {
        Set<String> tagStrings = dish.getTags() != null
                ? dish.getTags().stream()
                .map(FoodTag::tag)
                .collect(Collectors.toSet())
                : Set.of();

        return new DishProjectionDto(
                dish.getDishId().toString(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getPictureUrl(),
                dish.getType(),
                dish.getStatus().toString(),
                tagStrings
        );
    }
}
