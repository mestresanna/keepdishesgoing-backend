package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.BasketNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.DishProjectionNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.in.basket.AddDishToBasketCommand;
import kdg.programming6.keepdishesgoing.order.port.in.basket.AddDishtoBasketUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.basket.LoadBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.dish.LoadDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.basket.SaveBasketPort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddDishToABasketUseCaseImpl implements AddDishtoBasketUseCase {
    private final SaveBasketPort saveBasketPort;
    private final LoadBasketPort loadBasketPort;
    private final LoadDishProjectionPort loadDishPort;

    public AddDishToABasketUseCaseImpl(SaveBasketPort saveBasketPort, LoadBasketPort loadBasketPort, LoadDishProjectionPort loadDishPort) {
        this.saveBasketPort = saveBasketPort;
        this.loadBasketPort = loadBasketPort;
        this.loadDishPort = loadDishPort;
    }

    @Override
    public Basket addDishtoBasket(AddDishToBasketCommand command) {
        Basket basket;
        if (command.basketId() == null) {
            basket = Basket.create(command.restaurantId());
        } else{
            basket = loadBasketPort.loadBasket(command.basketId())
                .orElseThrow(() -> new BasketNotFoundException("basket not found" +  command.basketId()));
        }
        DishProjection dish = loadDishPort.loadbyIdByMenu(command.dishId(), command.menuId()).orElseThrow(() -> new DishProjectionNotFoundException("Dish not found" + command.dishId()));
        basket.addDish(new DishProjId(command.menuId(),command.dishId()), dish.getName(), command.restaurantId(), dish.getPrice(), command.quantity());
        return saveBasketPort.saveBasket(basket);
    }
}
