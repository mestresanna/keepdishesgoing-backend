package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderItem;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.DishNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.RestaurantOrderCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.RestaurantOrderCreatedProjector;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadDishPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantOrderCreatedImpl implements RestaurantOrderCreatedProjector {
    private final LoadDishPort loadDishPort;
    private final List<SaveRestaurantOrderPort> saveRestaurantOrderPort;
    public RestaurantOrderCreatedImpl(LoadDishPort loadDishPort, List<SaveRestaurantOrderPort> saveRestaurantOrderPort){
        this.loadDishPort = loadDishPort;
        this.saveRestaurantOrderPort = saveRestaurantOrderPort;
    }

    @Override
    public RestaurantOrder saveRestaurantOrder(RestaurantOrderCommand command) {
        List<RestaurantOrderItem> restaurantOrderItems = command.items().stream()
                .map(itemCmd -> {
                    Dish dish = loadDishPort.loadById(itemCmd.dishId()).orElseThrow(() -> new DishNotFoundException("Dish not found" + itemCmd.dishId()));
                    return new RestaurantOrderItem(
                        new DishId(itemCmd.dishId()),
                        dish.getName(),
                        itemCmd.quantity(),
                        itemCmd.priceAtOrder());
                }
                )
                .toList();
        RestaurantOrder restaurantOrder = new RestaurantOrder(command.orderId(), new RestaurantId(command.restaurantId()), command.customerName(), command.deliveryAddress(), restaurantOrderItems, command.totalPrice());
        saveRestaurantOrderPort.forEach(port -> port.save(restaurantOrder));
        return restaurantOrder;
    }
}
