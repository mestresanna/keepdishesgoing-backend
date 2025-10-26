package kdg.programming6.keepdishesgoing.order.port.in.order;


public interface OrderChangedProjector {
    void updateOrderStatus(OrderChangeStatusCommand  command);

}
