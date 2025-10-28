package kdg.programming6.keepdishesgoing.order.domain;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;

import java.math.BigDecimal;

public class BasketItems {
    private DishProjId dishId;
    private String dishName;
    private BigDecimal priceDish;
    private Integer quantity;

    public BasketItems(DishProjId dishId, String dishName, BigDecimal priceDish, Integer quantity) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.priceDish = priceDish;
        this.quantity = quantity;
    }
    public void addQuantity(int qty) {
        this.quantity += qty;
    }

    public void restQuantity(int qty) {
        this.quantity -= qty;
    }

    public BigDecimal totalPrice() {
        return priceDish.multiply(BigDecimal.valueOf(quantity));
    }

    public String getDishName() {
        return dishName;
    }

    public DishProjId getDishId() {
        return dishId;
    }

    public BigDecimal getPriceAtAddition() {
        return priceDish;
    }

    public int getQuantity() { return quantity; }
}
