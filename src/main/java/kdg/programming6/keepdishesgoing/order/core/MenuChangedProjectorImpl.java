package kdg.programming6.keepdishesgoing.order.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.adapter.in.MenuChangedListener;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.port.in.CreatedMenuProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.MenuChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.UpdatedMenuProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.out.SaveMenuProjectionPort;
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
        MenuProjection menu = new MenuProjection(
                command.id(), command.restaurantId(), null, 0.0
        );
        saveMenuProjectionPort.save(menu);
    }

    @Override
    @Transactional
    public void projectUpdatedMenu(UpdatedMenuProjectionCommand command) {
        MenuProjection menu = new MenuProjection(
                command.id(), command.restaurantId(), command.avgPrice()
        );
        saveMenuProjectionPort.save(menu);
    }
}
