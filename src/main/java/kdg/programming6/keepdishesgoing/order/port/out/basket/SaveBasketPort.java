package kdg.programming6.keepdishesgoing.order.port.out.basket;

import kdg.programming6.keepdishesgoing.order.domain.Basket;

public interface SaveBasketPort {
    Basket saveBasket(Basket basket);
}
