package kdg.programming6.keepdishesgoing.order.port.in.basket;

import kdg.programming6.keepdishesgoing.order.domain.Basket;

import java.util.UUID;

public interface FindBasketUseCase {
    Basket findById(UUID basketId);
}
