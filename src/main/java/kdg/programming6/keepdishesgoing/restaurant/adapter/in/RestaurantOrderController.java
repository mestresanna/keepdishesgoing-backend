package kdg.programming6.keepdishesgoing.restaurant.adapter.in;


import jakarta.validation.Valid;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.OrderStatusUpdateDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.RejectOrderRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.ManageOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant/orders")
public class RestaurantOrderController {

    private final ManageOrderUseCase manageOrderUseCase;

    public RestaurantOrderController(ManageOrderUseCase manageOrderUseCase) {
        this.manageOrderUseCase = manageOrderUseCase;
    }

    @PostMapping("/{orderId}/accept")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<OrderStatusUpdateDto> acceptOrder(@PathVariable UUID orderId) {
        manageOrderUseCase.acceptOrder(orderId);
        return ResponseEntity.ok(OrderStatusUpdateDto.accepted(orderId));
    }

    @PostMapping("/{orderId}/reject")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<OrderStatusUpdateDto> rejectOrder(
            @PathVariable UUID orderId,
            @Valid @RequestBody RejectOrderRequest request) {

        manageOrderUseCase.rejectOrder(orderId, request.reason());
        return ResponseEntity.ok(OrderStatusUpdateDto.rejected(orderId, request.reason()));
    }

    @PostMapping("/{orderId}/start-preparation")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<OrderStatusUpdateDto> startPreparation(@PathVariable UUID orderId) {
        manageOrderUseCase.startPreparation(orderId);
        return ResponseEntity.ok(OrderStatusUpdateDto.inPreparation(orderId));
    }

    @PostMapping("/{orderId}/mark-ready")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<OrderStatusUpdateDto> markReady(@PathVariable UUID orderId) {
        manageOrderUseCase.markReady(orderId);
        return ResponseEntity.ok(OrderStatusUpdateDto.ready(orderId));
    }
}
