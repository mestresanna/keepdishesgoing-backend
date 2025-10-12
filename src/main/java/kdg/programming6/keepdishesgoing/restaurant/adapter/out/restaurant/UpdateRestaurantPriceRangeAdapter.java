package kdg.programming6.keepdishesgoing.restaurant.adapter.out.restaurant;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.events.MenuUpdatedEvent;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;
import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadMenuPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.RestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.UpdateMenuPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateRestaurantPriceRangeAdapter implements UpdateMenuPort {
    private static final Logger logger = LoggerFactory.getLogger(UpdateRestaurantPriceRangeAdapter.class);

    private final LoadRestaurantPort loadRestaurantPort;
    private final RestaurantPort saveRestaurantPort;
    private final LoadMenuPort loadMenuPort;

    public UpdateRestaurantPriceRangeAdapter(
            LoadRestaurantPort loadRestaurantPort,
            RestaurantPort saveRestaurantPort,
            LoadMenuPort loadMenuPort) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.saveRestaurantPort = saveRestaurantPort;
        this.loadMenuPort = loadMenuPort;
    }

    @Transactional
    @Override
    public void update(Menu menu) {
        menu.getUncommittedEvents().stream()
                .filter(MenuUpdatedEvent.class::isInstance)
                .map(MenuUpdatedEvent.class::cast)
                .forEach(this::handle);
    }

    private void handle(MenuUpdatedEvent event) {
        var restaurantOpt = loadRestaurantPort.loadById(event.restaurantId());
        var menuOpt = loadMenuPort.loadMenuByRestaurantId(new RestaurantId(event.restaurantId()));

        if (restaurantOpt.isEmpty() || menuOpt.isEmpty()) {
            logger.info("restaurant not found");
            return;}

        Restaurant restaurant = restaurantOpt.get();
        var menu = menuOpt.get();

        long avgPrice = Math.round(menu.getDishes().stream()
                .mapToDouble(Dish::getPrice)
                .average()
                .orElse(0.0));

        restaurant.setPriceRange(avgPrice);
        saveRestaurantPort.save(restaurant);

        logger.info("Restaurant {} price range updated to {}",
                restaurant.getRestaurantId(), restaurant.getPriceRange());
    }
}
