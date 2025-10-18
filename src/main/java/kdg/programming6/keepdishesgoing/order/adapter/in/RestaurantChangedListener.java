package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.common.events.RestaurantCreatedEvent;
import kdg.programming6.keepdishesgoing.common.events.RestaurantUpdatedEvent;
import kdg.programming6.keepdishesgoing.order.domain.vo.OpeningHoursProjection;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.CreatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.RestaurantChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.UpdatedRestaurantProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantChangedListener {
    private final Logger logger = LoggerFactory.getLogger(RestaurantChangedListener.class);
    private final RestaurantChangedProjector projector;
    public RestaurantChangedListener(RestaurantChangedProjector projector) {
        this.projector = projector;
    }

    @EventListener(RestaurantCreatedEvent.class)
    public void onRestaurantCreated(RestaurantCreatedEvent event) {
        logger.debug("Restaurant Created Event");
        projector.projectCreatedRestaurant(new CreatedRestaurantProjectionCommand(
                event.restaurantId(),
                event.ownerId(),
                event.name(),
                event.street(),
                event.number(),
                event.city(),
                event.country(),
                event.pictureUrls(),
                new OpeningHoursProjection(event.openingHours()),
                event.typeOfCuisine(),
                event.contactEmail(),
                event.defaultPreparationTimeInMinutes(),
                event.priceRange()
                ));
    }

    @EventListener(RestaurantUpdatedEvent.class)
    public void onRestaurantUpdated(RestaurantUpdatedEvent event) {
        logger.debug("Restaurant Updated Event");
        projector.projectUpdatedRestaurant(new UpdatedRestaurantProjectionCommand(
                event.restaurantId(),
                event.ownerId(),
                event.name(),
                event.street(),
                event.number(),
                event.city(),
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
