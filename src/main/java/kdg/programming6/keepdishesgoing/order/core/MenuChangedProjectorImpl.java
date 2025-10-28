package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjectionId;
import kdg.programming6.keepdishesgoing.order.port.in.menu.CreatedMenuProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.menu.MenuChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.menu.UpdatedMenuProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.out.menu.SaveMenuProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MenuChangedProjectorImpl implements MenuChangedProjector {
    private final Logger logger = LoggerFactory.getLogger(MenuChangedProjectorImpl.class);
    private final SaveMenuProjectionPort saveMenuProjectionPort;
    public MenuChangedProjectorImpl(SaveMenuProjectionPort saveMenuProjectionPort) {
        this.saveMenuProjectionPort = saveMenuProjectionPort;
    }


    @Override
    @Transactional
    public void projectCreatedMenu(CreatedMenuProjectionCommand command) {
        logger.debug("Projecting created menu");
        MenuProjection menu = new MenuProjection(
                command.id(), new RestaurantProjectionId(command.restaurantId()), null, null
        );
        saveMenuProjectionPort.save(menu);
    }

    @Override
    @Transactional
    public void projectUpdatedMenu(UpdatedMenuProjectionCommand command) {
        logger.debug("Projecting updated menu");
        MenuProjection menu = new MenuProjection(
                command.id(), new RestaurantProjectionId(command.restaurantId()), command.avgPrice()
        );
        saveMenuProjectionPort.save(menu);
    }
}
