package kdg.programming6.keepdishesgoing.restaurant.core;

import jakarta.transaction.Transactional;
import kdg.programming6.keepdishesgoing.common.vo.Coordinates;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantOrderNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.LoadRestaurantOrderPort;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.ManageOrderUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.out.GeocodingPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import kdg.programming6.keepdishesgoing.restaurant.port.out.SaveRestaurantOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ManageOrderImpl implements ManageOrderUseCase {
    private final LoadRestaurantOrderPort loadRestaurantOrderPort;
    private final List<SaveRestaurantOrderPort> saveRestaurantOrderPort;
    private final GeocodingPort geocodingPort;
    private final LoadRestaurantPort loadRestaurantPort;
    public ManageOrderImpl(LoadRestaurantOrderPort loadRestaurantOrderPort, List<SaveRestaurantOrderPort> saveRestaurantOrderPort, GeocodingPort geocodingPort, LoadRestaurantPort loadRestaurantPort
    ) {
        this.loadRestaurantOrderPort = loadRestaurantOrderPort;
        this.saveRestaurantOrderPort = saveRestaurantOrderPort;
        this.geocodingPort = geocodingPort;
        this.loadRestaurantPort = loadRestaurantPort;
    }

    @Override
    public void acceptOrder(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));
        Restaurant restaurant = loadRestaurantPort.loadById(order.getRestaurantId().restaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + orderId));
        Coordinates pickupCoords = geocodingPort
                .getCoordinates(restaurant.getAddress())
                .orElseThrow(() -> new IllegalStateException("Could not geocode pickup address"));

        Coordinates dropoffCoords = geocodingPort
                .getCoordinates(order.getDeliveryAddress())
                .orElseThrow(() -> new IllegalStateException("Could not geocode dropoff address"));
        order.accept(restaurant.getAddress(), pickupCoords, order.getDeliveryAddress(), dropoffCoords);
        saveRestaurantOrderPort.forEach(port -> port.save(order));
        order.clearDomainEvents();
    }

    @Override
    public void rejectOrder(UUID orderId, String reason) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));
        order.reject(reason);
        saveRestaurantOrderPort.forEach(port -> port.save(order));
        order.clearDomainEvents();
    }

    @Override
    public void startPreparation(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));

        order.startPreparation();
        saveRestaurantOrderPort.forEach(port -> port.save(order));
        order.clearDomainEvents();
    }

    @Override
    public void markReady(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));
        order.markReady();
        saveRestaurantOrderPort.forEach(port -> port.save(order));
        order.clearDomainEvents();

    }
    @Override
    public void markOrderPickedUp(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));
        order.markPickedUp();
        saveRestaurantOrderPort.forEach(port -> port.save(order));
        order.clearDomainEvents();
    }

    @Override
    public void markOrderDelivered(UUID orderId) {
        RestaurantOrder order = loadRestaurantOrderPort.findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException("Order not found: " + orderId));
        order.markDelivered();
        saveRestaurantOrderPort.forEach(port -> port.save(order));
        order.clearDomainEvents();
    }


}
