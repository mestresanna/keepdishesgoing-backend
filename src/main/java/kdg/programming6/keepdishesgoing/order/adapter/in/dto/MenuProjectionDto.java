package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.List;
import java.util.UUID;

public record MenuProjectionDto (
        UUID menuId,
        List<DishProjectionDto> dishes
){
}
