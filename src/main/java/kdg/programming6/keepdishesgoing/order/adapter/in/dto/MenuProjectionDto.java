package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.List;

public record MenuProjectionDto (
        List<DishProjectionDto> dishes

){
}
