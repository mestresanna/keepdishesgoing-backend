package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.BasketNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.DishProjectionNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.in.basket.RemoveDishBasketCommand;
import kdg.programming6.keepdishesgoing.order.port.in.basket.RemoveDishBasketUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.DeleteBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.LoadBasketPort;
import kdg.programming6.keepdishesgoing.order.port.out.LoadDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.SaveBasketPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class RemoveDishToBasketUseCaseImpl implements RemoveDishBasketUseCase {
    private final SaveBasketPort saveBasketPort;
    private final DeleteBasketPort deleteBasketPort;
    private final LoadBasketPort loadBasketPort;
    private final LoadDishProjectionPort loadDishPort;

    public RemoveDishToBasketUseCaseImpl(SaveBasketPort saveBasketPort, DeleteBasketPort deleteBasketPort, LoadBasketPort loadBasketPort, LoadDishProjectionPort loadDishPort) {
        this.saveBasketPort = saveBasketPort;
        this.deleteBasketPort = deleteBasketPort;
        this.loadBasketPort = loadBasketPort;
        this.loadDishPort = loadDishPort;
    }


    @Override
    public Basket removeDishtoBasket(RemoveDishBasketCommand command) {
        Basket basket = loadBasketPort.loadBasket(command.basketId())
                    .orElseThrow(() -> new BasketNotFoundException("basket not found" +  command.basketId()));

        DishProjection dish = loadDishPort.loadbyIdByMenu(command.dishId(), command.menuId()).orElseThrow(() -> new DishProjectionNotFoundException("Dish not found" + command.dishId()));
        basket.removeDish(new DishProjId(command.menuId(),dish.getDishId()));
        if (basket.isBasketEmpty()) {
            deleteBasketPort.deleteBasket(basket);
            return null;
        }else{
        return saveBasketPort.saveBasket(basket);    }}

}
