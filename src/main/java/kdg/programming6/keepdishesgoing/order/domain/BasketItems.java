package kdg.programming6.keepdishesgoing.order.domain;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;

import java.math.BigDecimal;

public class BasketItems {
    private DishProjId dishId;
    private BigDecimal priceDish;
    private Integer quantity;

    public BasketItems(DishProjId dishId, BigDecimal priceDish, Integer quantity) {
        this.dishId = dishId;
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


    public DishProjId getDishId() {
        return dishId;
    }

    public BigDecimal getPriceAtAddition() {
        return priceDish;
    }

    public int getQuantity() { return quantity; }
}
