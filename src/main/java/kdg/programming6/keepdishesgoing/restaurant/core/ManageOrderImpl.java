package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantOrderNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.LoadRestaurantOrderPort;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.ManageOrderUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ManageOrderImpl implements ManageOrderUseCase {
    private final LoadRestaurantOrderPort loadRestaurantOrderPort;
    private final List<SaveRestaurantOrderPort> saveRestaurantOrderPort;

    public ManageOrderImpl(
            LoadRestaurantOrderPort loadRestaurantOrderPort,
            List<SaveRestaurantOrderPort> saveRestaurantOrderPort
            ) {
        this.loadRestaurantOrderPort = loadRestaurantOrderPort;
        this.saveRestaurantOrderPort = saveRestaurantOrderPort;
    }

    @Override
    public void acceptOrder(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));

        order.accept();
        saveRestaurantOrderPort.forEach(port -> port.save(order));
    }

    @Override
    public void rejectOrder(UUID orderId, String reason) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));

        order.reject(reason);
        saveRestaurantOrderPort.forEach(port -> port.save(order));

    }

    @Override
    public void startPreparation(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));

        order.startPreparation();
        saveRestaurantOrderPort.forEach(port -> port.save(order));

    }

    @Override
    public void markReady(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));

        order.markReady();
        saveRestaurantOrderPort.forEach(port -> port.save(order));

    }
}
