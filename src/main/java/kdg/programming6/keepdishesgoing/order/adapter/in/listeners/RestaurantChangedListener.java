package kdg.programming6.keepdishesgoing.order.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.restaurant.RestaurantCreatedEvent;
import kdg.programming6.keepdishesgoing.common.events.restaurant.RestaurantUpdatedEvent;
import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.CreatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.RestaurantChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.UpdatedRestaurantProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantChangedListener {
    private final Logger logger = LoggerFactory.getLogger(RestaurantChangedListener.class);
    private final RestaurantChangedProjector projector;
    public RestaurantChangedListener(RestaurantChangedProjector projector) {
        this.projector = projector;
    }

    @ApplicationModuleListener
    public void onRestaurantCreated(RestaurantCreatedEvent event) {
        logger.info("Restaurant Created Event");
        projector.projectCreatedRestaurant(new CreatedRestaurantProjectionCommand(
                event.restaurantId(),
                event.ownerId(),
                event.name(),
                event.street(),
                event.number(),
                event.city(),
                event.postalCode(),
                event.country(),
                event.pictureUrls(),
                new OpeningHoursProjection(event.openingHours()),
                event.typeOfCuisine(),
                event.contactEmail(),
                event.defaultPreparationTimeInMinutes(),
                event.priceRange()
                ));
    }

    @ApplicationModuleListener
    public void onRestaurantUpdated(RestaurantUpdatedEvent event) {
        logger.info("Restaurant Updated Event");
        projector.projectUpdatedRestaurant(new UpdatedRestaurantProjectionCommand(
                event.restaurantId(),
                event.ownerId(),
                event.name(),
                event.street(),
                event.number(),
                event.city(),
                event.postalCode(),
                event.country(),
                event.pictureUrls(),
                new OpeningHoursProjection(event.openingHours()),
                event.typeOfCuisine(),
                event.contactEmail(),
                event.defaultPreparationTimeInMinutes(),
                event.priceRange()
        ));
    }
}
