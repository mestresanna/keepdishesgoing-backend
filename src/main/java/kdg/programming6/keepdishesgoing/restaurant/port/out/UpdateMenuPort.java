package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;

public interface UpdateMenuPort {
    void update(Menu menu);
}