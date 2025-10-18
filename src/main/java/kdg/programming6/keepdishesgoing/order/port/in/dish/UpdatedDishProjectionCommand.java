package kdg.programming6.keepdishesgoing.order.port.in.dish;

import java.util.UUID;

public record UpdatedDishProjectionCommand (UUID dishId, String status){
}
