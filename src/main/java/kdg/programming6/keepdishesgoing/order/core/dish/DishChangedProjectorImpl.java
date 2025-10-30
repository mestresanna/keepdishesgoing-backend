package kdg.programming6.keepdishesgoing.order.core.dish;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjectionId;
import kdg.programming6.keepdishesgoing.order.domain.projections.StatusDish;
import kdg.programming6.keepdishesgoing.order.port.in.dish.CreatedDishProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.dish.DishChangedProjector;
import kdg.programming6.keepdishesgoing.order.port.in.dish.UnpublishDishProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.in.dish.UpdatedDishProjectionCommand;
import kdg.programming6.keepdishesgoing.order.port.out.dish.DeleteDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.dish.LoadDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.dish.SaveDishProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.menu.LoadMenuProjectionPort;
import kdg.programming6.keepdishesgoing.order.port.out.menu.SaveMenuProjectionPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishChangedProjectorImpl implements DishChangedProjector {
    private final SaveDishProjectionPort saveDishProjectionPort;
    private final DeleteDishProjectionPort deleteDishProjectionPort;
    private final SaveMenuProjectionPort saveMenuProjectionPort;
    private final LoadMenuProjectionPort loadMenuProjectionPort;
    private final LoadDishProjectionPort loadDishProjectionPort;

    public DishChangedProjectorImpl(SaveDishProjectionPort saveDishProjectionPort, DeleteDishProjectionPort deleteDishProjectionPort, SaveMenuProjectionPort saveMenuProjectionPort, LoadMenuProjectionPort loadMenuProjectionPort, LoadDishProjectionPort loadDishProjectionPort) {
        this.saveDishProjectionPort = saveDishProjectionPort;
        this.deleteDishProjectionPort = deleteDishProjectionPort;
        this.saveMenuProjectionPort = saveMenuProjectionPort;
        this.loadMenuProjectionPort = loadMenuProjectionPort;
        this.loadDishProjectionPort = loadDishProjectionPort;
    }


    @Override
    @Transactional
    public void projectPublishedDish(CreatedDishProjectionCommand command) {
        MenuProjection menuProjection = loadMenuProjectionPort.loadbyId(command.menuId()).orElseThrow(IllegalStateException::new);
        DishProjection dishProjection = new DishProjection(command.dishId(), command.name(), new RestaurantProjectionId(command.restaurantId()), command.type(), command.tags(), command.description(), command.price(), command.pictureUrl(), StatusDish.valueOf(command.status().toUpperCase()));
        saveDishProjectionPort.saveDishProjection(dishProjection, menuProjection.getId());
        menuProjection.addDish(dishProjection);
        saveMenuProjectionPort.save(menuProjection);
    }

    @Override
    @Transactional
    public void projectUnpublishDish(UnpublishDishProjectionCommand command) {
        DishProjection dishProjection = loadDishProjectionPort.loadbyIdByMenu(command.dishId(), command.menuId()).orElseThrow(IllegalStateException::new);
        deleteDishProjectionPort.deleteDishProjection(dishProjection, command.menuId());
        MenuProjection menuProjection = loadMenuProjectionPort.loadbyId(command.menuId()).orElseThrow(IllegalStateException::new);
        menuProjection.deleteDish(dishProjection);
        saveMenuProjectionPort.save(menuProjection);
    }

    @Override
    @Transactional
    public void projectChangedStateDish(UpdatedDishProjectionCommand command) {
        List<DishProjection> dishProjection = loadDishProjectionPort.loadAllWithDishId(command.dishId());

        for (DishProjection dish : dishProjection) {
            dish.changeStateDish();
        }
        saveDishProjectionPort.saveDishProjections(dishProjection);
    }
}
