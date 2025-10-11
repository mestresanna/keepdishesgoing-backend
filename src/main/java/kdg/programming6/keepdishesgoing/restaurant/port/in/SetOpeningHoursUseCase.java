package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;

public interface SetOpeningHoursUseCase {
    Restaurant setOpeningHours(SetOpeningHoursCommand command) throws RestaurantNotFoundException;
}
