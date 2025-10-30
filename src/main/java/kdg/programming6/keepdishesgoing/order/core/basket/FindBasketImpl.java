package kdg.programming6.keepdishesgoing.order.core.basket;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.BasketNotFoundException;
import kdg.programming6.keepdishesgoing.order.port.in.basket.FindBasketUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.basket.LoadBasketPort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Transactional
public class FindBasketImpl implements FindBasketUseCase {
    private final LoadBasketPort loadBasketPort;
    public FindBasketImpl(LoadBasketPort loadBasketPort) {
        this.loadBasketPort = loadBasketPort;
    }
    @Override
    public Basket findById(UUID basketId) {
        return loadBasketPort.loadBasket(basketId).orElseThrow(() -> new BasketNotFoundException("Basket not found: " + basketId));
    }
}
