package kdg.programming6.keepdishesgoing.order.domain;

import kdg.programming6.keepdishesgoing.order.domain.exceptions.DifferentRestaurantException;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.DishUnavailableException;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjId;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Basket {
    private UUID basketId;
    private UUID restaurantId;
    private List<BasketItems> items = new ArrayList<>();
    private BigDecimal totalPrice;
    private BasketStatus basketStatus;
    private String message;

    public Basket(UUID basketId, UUID restaurantId, List<BasketItems> items, BigDecimal totalPrice, BasketStatus basketStatus, String message) {
        this.basketId = basketId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.basketStatus = basketStatus;
        this.message = message;
    }

    public static Basket create(UUID restaurantId){
        Basket basket = new Basket(UUID.randomUUID(), restaurantId, new ArrayList<BasketItems>(), null, BasketStatus.IN_PROGRESS, null);
        return basket;
    }

    public void addDish(DishProjId dishId, String nameDish, UUID dishRestaurantId, BigDecimal price, int quantity) {
        if (this.restaurantId == null) {
            this.restaurantId = dishRestaurantId;
        } else if (!this.restaurantId.equals(dishRestaurantId)) {
            throw new DifferentRestaurantException();
        }
        if (!items.isEmpty()) {
        BasketItems existing = items.stream()
                .filter(i -> i.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);

            if (existing != null) {
                existing.addQuantity(quantity);}
            else {
                items.add(new BasketItems(dishId,nameDish, price, quantity));
            }
        } else {
            items.add(new BasketItems(dishId,nameDish, price, quantity));
        }

        recalculateTotalPrice();
    }

    public void removeDish(DishProjId dishId) {
        items.removeIf(i -> i.getDishId().equals(dishId));
        recalculateTotalPrice();

    }
    public void validateItemsAvailability(Map<UUID, DishProjection> dishProjections) {
        List<UUID> unavailableDishes = items.stream()
                .filter(item -> {
                    DishProjection dish = dishProjections.get(item.getDishId().dishId());
                    return dish == null || !dish.isDishAvailable();
                })
                .map(item -> item.getDishId().dishId())
                .collect(Collectors.toList());

        if (!unavailableDishes.isEmpty()) {
            throw new DishUnavailableException(unavailableDishes);
        }
    }

    public void markAsReady() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot mark an empty basket as ready");
        }
        this.basketStatus = BasketStatus.READY;
    }

    public void markAsOrdered() {
        if (basketStatus != BasketStatus.READY && basketStatus != BasketStatus.IN_PROGRESS) {
            throw new IllegalStateException("Can only checkout baskets that are ready or in progress");
        }
        this.basketStatus = BasketStatus.ORDERED;
    }
    private void recalculateTotalPrice() {
        this.totalPrice = items.stream()
                .map(BasketItems::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isBasketEmpty() {
        return items.isEmpty();
    }

    public UUID getBasketId() {
        return basketId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public List<BasketItems> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BasketStatus getBasketStatus() {
        return basketStatus;
    }

    public String getMessage() {
        return message;
    }
}
