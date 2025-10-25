package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.Basket;

import java.util.Optional;
import java.util.UUID;

public interface LoadBasketPort {
    Optional<Basket> loadBasket(UUID basketId);
}
