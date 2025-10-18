package kdg.programming6.keepdishesgoing.order.port.in.dish;

import java.util.UUID;

public record UnpublishDishProjectionCommand (UUID dishId, UUID menuId){
}
