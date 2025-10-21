package kdg.programming6.keepdishesgoing.order.adapter.in.listeners;

import kdg.programming6.keepdishesgoing.common.events.DishPublishedEvent;
import kdg.programming6.keepdishesgoing.common.events.DishStateChangedEvent;
import kdg.programming6.keepdishesgoing.common.events.DishUnpublishedEvent;
import kdg.programming6.keepdishesgoing.order.port.in.dish.CreatedDishProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.dish.DishChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.dish.UnpublishDishProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.dish.UpdatedDishProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class DishChangedListener {
    private final Logger logger = LoggerFactory.getLogger(DishChangedListener.class);
    private final DishChangedProjector dishChangedProjector;

    public DishChangedListener(DishChangedProjector dishChangedProjector) {
        this.dishChangedProjector = dishChangedProjector;
    }

    @ApplicationModuleListener
    public void onDishPublished(DishPublishedEvent event) {
        logger.info("Dish Published Event received");
        dishChangedProjector.projectPublishedDish(new CreatedDishProjectionCommand(event.dishId(), event.menuId(), event.name(), event.restaurantId(), event.type(), event.tags(), event.description(), event.price(), event.pictureUrl(), event.status()));
    }

    @ApplicationModuleListener
    public void onDishUnpublished(DishUnpublishedEvent event) {
        logger.info("Dish Unpublished Event received");
        dishChangedProjector.projectUnpublishDish(new UnpublishDishProjectionCommand(event.dishId(), event.menuId()));
    }

    @ApplicationModuleListener
    public void onDishStateChanged(DishStateChangedEvent event){
        logger.debug("Dish State Changed Event received");
        dishChangedProjector.projectChangedStateDish(new UpdatedDishProjectionCommand(event.dishId(), event.state()));
    }
}
