package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

public interface CreateRestaurant {

    // As an owner, I want to create my restaurant by submitting name, full address, contact email, picture(s) url, default preparation time, type of cuisine, and opening hours.
    void createRestaurant(Restaurant restaurant);
}
