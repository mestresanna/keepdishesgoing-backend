package kdg.programming6.keepdishesgoing.order.port.in.basket;

import kdg.programming6.keepdishesgoing.order.domain.Basket;

public interface AddDishtoBasketUseCase {
    Basket addDishtoBasket(AddDishToBasketCommand command);
}
