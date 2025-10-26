package kdg.programming6.keepdishesgoing.order.domain.exceptions;

import java.util.List;
import java.util.UUID;

public class DishUnavailableException extends RuntimeException {
    private final List<UUID> unavailableDishIds;

    public DishUnavailableException(List<UUID> unavailableDishIds) {
        super("The following dishes are unavailable: " + unavailableDishIds);
        this.unavailableDishIds = unavailableDishIds;
    }

    public List<UUID> getUnavailableDishIds() {
        return unavailableDishIds;
    }
}
