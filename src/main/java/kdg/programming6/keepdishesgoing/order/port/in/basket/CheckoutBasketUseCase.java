package kdg.programming6.keepdishesgoing.order.port.in.basket;

import kdg.programming6.keepdishesgoing.order.domain.Basket;

public interface CheckoutBasketUseCase {
    void checkout(CheckoutBasketCommand command);
}
