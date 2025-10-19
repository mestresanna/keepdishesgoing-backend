package kdg.programming6.keepdishesgoing.order.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.MenuCreatedEvent;
import kdg.programming6.keepdishesgoing.common.events.MenuUpdatedEvent;
import kdg.programming6.keepdishesgoing.order.port.in.menu.CreatedMenuProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.menu.MenuChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.menu.UpdatedMenuProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MenuChangedListener {
    private final Logger logger = LoggerFactory.getLogger(MenuChangedListener.class);
    private final MenuChangedProjector menuChangedProjector;
    public MenuChangedListener(MenuChangedProjector menuChangedProjector) {
        this.menuChangedProjector = menuChangedProjector;
    }

    @EventListener(MenuCreatedEvent.class)
    public void onMenuCreated(MenuCreatedEvent event) {
        logger.debug("MenuCreatedEvent");

        menuChangedProjector.projectCreatedMenu(new CreatedMenuProjectionCommand(
                event.menuId(),
                event.restaurantId()));
    }

    @EventListener(MenuUpdatedEvent.class)
    public void onMenuUpdated(MenuUpdatedEvent event) {
        logger.debug("MenuUpdatedEvent");


        menuChangedProjector.projectUpdatedMenu(new UpdatedMenuProjectionCommand(
                event.menuId(),
                event.restaurantId(),
                event.avgPrice()
        ));
    }

}
