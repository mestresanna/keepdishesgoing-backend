package kdg.programming6.keepdishesgoing.order.port.in.basket;


import kdg.programming6.keepdishesgoing.order.domain.Order;

public interface CheckoutBasketUseCase {
    Order checkout(CheckoutBasketCommand command);
}
