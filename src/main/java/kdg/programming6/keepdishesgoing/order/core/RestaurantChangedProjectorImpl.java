package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.port.in.CreatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.RestaurantChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.UpdatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.out.SaveRestaurantProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantChangedProjectorImpl implements RestaurantChangedProjector {
    private final Logger logger = LoggerFactory.getLogger(RestaurantChangedProjectorImpl.class);

    private final SaveRestaurantProjectionPort saveRestaurantProjectionPorts;

    public RestaurantChangedProjectorImpl(SaveRestaurantProjectionPort saveRestaurantProjectionPorts) {
        this.saveRestaurantProjectionPorts = saveRestaurantProjectionPorts;
    }

    @Override
    @Transactional
    public void projectCreatedRestaurant(CreatedRestaurantProjectionCommand command) {
        RestaurantProjection restaurant = new RestaurantProjection(command.restaurantId(), command.ownerId(), command.name(), command.street(), command.number(), command.city(), command.country(), command.pictureUrls(), command.openingHours(), command.typeOfCuisine(), command.contactEmail(), command.defaultPreparationTimeInMinutes(), command.priceRange());
        saveRestaurantProjectionPorts.save(restaurant);
    }

    @Override
    @Transactional
    public void projectUpdatedRestaurant(UpdatedRestaurantProjectionCommand command) {

    }
}
