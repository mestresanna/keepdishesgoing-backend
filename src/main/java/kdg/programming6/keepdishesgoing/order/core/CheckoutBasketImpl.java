package kdg.programming6.keepdishesgoing.order.core;

import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.BasketNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.DishProjectionNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.in.basket.CheckoutBasketCommand;
import kdg.programming6.keepdishesgoing.order.port.in.basket.CheckoutBasketUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.LoadBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.LoadDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveOrderPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CheckoutBasketImpl implements CheckoutBasketUseCase {
    private final LoadBasketPort loadBasketPort;
    private final LoadDishProjectionPort loadDishProjectionPort;
    private final List<SaveOrderPort> saveOrderPort;
    private final SaveBasketPort saveBasketPort;

    public CheckoutBasketImpl(
            LoadBasketPort loadBasketPort,
            LoadDishProjectionPort loadDishProjectionPort,
            List<SaveOrderPort> saveOrderPort,
            SaveBasketPort saveBasketPort) {
        this.loadBasketPort = loadBasketPort;
        this.loadDishProjectionPort = loadDishProjectionPort;
        this.saveOrderPort = saveOrderPort;
        this.saveBasketPort = saveBasketPort;
    }

    @Override
    public void checkout(CheckoutBasketCommand command) {
        Basket basket = loadBasketPort.loadBasket(command.basketId())
                .orElseThrow(() -> new BasketNotFoundException("Basket not found: " + command.basketId()));

        Map<UUID, DishProjection> dishProjections = basket.getItems().stream()
                .map(item -> item.getDishId().dishId())
                .distinct()
                .collect(Collectors.toMap(
                        dishId -> dishId,
                        dishId -> loadDishProjectionPort.loadAllWithDishId(dishId).stream().findFirst().orElseThrow(() -> new DishProjectionNotFoundException("Dish not found: " + dishId))));

        basket.validateItemsAvailability(dishProjections);

        basket.markAsOrdered();
        saveBasketPort.saveBasket(basket);

        Order order = new Order(
                UUID.randomUUID(),
                basket,
                command.toCustomer(),
                command.message()
        );
        saveOrderPort.forEach( port -> port.save(order));
    }
}