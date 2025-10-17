package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.common.events.RestaurantCreatedEvent;
import kdg.programming6.keepdishesgoing.common.events.RestaurantUpdatedEvent;
import kdg.programming6.keepdishesgoing.order.adapter.in.mapper.OpeningHoursProjectionMapper;
import kdg.programming6.keepdishesgoing.order.port.in.CreatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.RestaurantChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.UpdatedRestaurantProjectionCommand;
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
        var openingHoursProjection = OpeningHoursProjectionMapper.fromJson(event.openingHours());

        projector.projectCreatedRestaurant(new CreatedRestaurantProjectionCommand(
                event.restaurantId(),
                event.ownerId(),
                event.name(),
                event.street(),
                event.number(),
                event.city(),
                event.country(),
                event.pictureUrls(),
                openingHoursProjection,
                event.typeOfCuisine(),
                event.contactEmail(),
                event.defaultPreparationTimeInMinutes(),
                event.priceRange()
                ));
    }

    @EventListener(RestaurantUpdatedEvent.class)
    public void onRestaurantUpdated(RestaurantUpdatedEvent event) {
        logger.debug("Restaurant Updated Event");
        var openingHoursProjection = OpeningHoursProjectionMapper.fromJson(event.openingHours());

        projector.projectUpdatedRestaurant(new UpdatedRestaurantProjectionCommand(
                event.restaurantId(),
                event.ownerId(),
                event.name(),
                event.street(),
                event.number(),
                event.city(),
                event.country(),
                event.pictureUrls(),
                openingHoursProjection,
                event.typeOfCuisine(),
                event.contactEmail(),
                event.defaultPreparationTimeInMinutes(),
                event.priceRange()
        ));
    }
}
