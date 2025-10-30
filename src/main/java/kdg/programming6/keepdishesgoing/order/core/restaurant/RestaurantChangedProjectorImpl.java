package kdg.programming6.keepdishesgoing.order.core.restaurant;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjectionId;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.CreatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.RestaurantChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.UpdatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.out.restaurant.SaveRestaurantProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        Address address = new Address( command.street(), command.number(), command.city(), command.postalCode(), command.country());
        RestaurantProjection restaurant = new RestaurantProjection(new RestaurantProjectionId(command.restaurantId()), command.ownerId(), command.name(),address, command.pictureUrls(), command.openingHours(), command.typeOfCuisine(), command.contactEmail(), command.defaultPreparationTimeInMinutes(), command.priceRange());
        saveRestaurantProjectionPorts.save(restaurant);
    }

    @Override
    @Transactional
    public void projectUpdatedRestaurant(UpdatedRestaurantProjectionCommand command) {
        Address address = new Address( command.street(), command.number(), command.city(), command.postalCode(), command.country());
        RestaurantProjection restaurant = new RestaurantProjection(new RestaurantProjectionId(command.restaurantId()), command.ownerId(), command.name(), address, command.pictureUrls(), command.openingHours(), command.typeOfCuisine(), command.contactEmail(), command.defaultPreparationTimeInMinutes(), command.priceRange());
        saveRestaurantProjectionPorts.save(restaurant);
    }
}
