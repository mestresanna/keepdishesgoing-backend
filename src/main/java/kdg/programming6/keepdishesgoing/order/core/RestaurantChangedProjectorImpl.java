package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.port.in.CreatedRestaurantProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.RestaurantChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.UpdatedRestaurantProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RestaurantChangedProjectorImpl implements RestaurantChangedProjector {
    private final Logger logger = LoggerFactory.getLogger(RestaurantChangedProjectorImpl.class);


    @Override
    @Transactional
    public void projectCreatedRestaurant(CreatedRestaurantProjectionCommand command) {

    }

    @Override
    @Transactional
    public void projectUpdatedRestaurant(UpdatedRestaurantProjectionCommand command) {

    }
}
