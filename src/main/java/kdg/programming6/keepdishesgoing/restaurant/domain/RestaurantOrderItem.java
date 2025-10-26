package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;

import java.math.BigDecimal;
import java.util.UUID;

public class RestaurantOrderItem {
    private DishId dishId;
    private String dishName;
    private int quantity;
    private BigDecimal priceAtOrder;

    public RestaurantOrderItem(DishId dishId, String dishName, int quantity, BigDecimal priceAtOrder) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    public DishId getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPriceAtOrder() { return priceAtOrder; }
}