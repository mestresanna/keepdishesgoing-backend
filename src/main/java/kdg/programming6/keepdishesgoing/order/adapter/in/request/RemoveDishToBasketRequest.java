package kdg.programming6.keepdishesgoing.order.adapter.in.request;

import java.util.UUID;

public record RemoveDishToBasketRequest(UUID menuId,
                                        UUID dishId) {
}
