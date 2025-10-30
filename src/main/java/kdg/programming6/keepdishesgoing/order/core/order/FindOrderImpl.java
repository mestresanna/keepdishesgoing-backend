package kdg.programming6.keepdishesgoing.order.core.order;

import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.OrderNotFoundException;
import kdg.programming6.keepdishesgoing.order.port.in.order.FindOrderUseCase;
import kdg.programming6.keepdishesgoing.order.port.out.order.LoadOrderPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class FindOrderImpl implements FindOrderUseCase {
    private final LoadOrderPort loadOrderPort;

    public FindOrderImpl(LoadOrderPort loadOrderPort) {
        this.loadOrderPort = loadOrderPort;
    }

    @Override
    public Order findById(UUID orderId) {
        return loadOrderPort.loadOrder(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found " + orderId));
    }
}
