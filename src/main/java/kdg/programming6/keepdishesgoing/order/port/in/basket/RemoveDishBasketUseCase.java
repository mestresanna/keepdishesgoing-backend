package kdg.programming6.keepdishesgoing.order.port.in.basket;

import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;

import java.util.UUID;

public interface RemoveDishBasketUseCase {
    Basket removeDishtoBasket(RemoveDishBasketCommand command);

}
