package kdg.programming6.keepdishesgoing.order.port.in.menu;

public interface MenuChangedProjector {
    void projectCreatedMenu(CreatedMenuProjectionCommand command);
    void projectUpdatedMenu(UpdatedMenuProjectionCommand command);

}
