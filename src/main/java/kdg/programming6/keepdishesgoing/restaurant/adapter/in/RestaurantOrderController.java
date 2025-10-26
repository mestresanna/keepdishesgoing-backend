package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.port.in.order.ManageOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant/orders")
public class RestaurantOrderController {
    
    private final ManageOrderUseCase manageOrderUseCase;

    public RestaurantOrderController(ManageOrderUseCase manageOrderUseCase) {
        this.manageOrderUseCase = manageOrderUseCase;
    }

    @PostMapping("/{orderId}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable UUID orderId) {
        manageOrderUseCase.acceptOrder(orderId);
        return ResponseEntity.ok(Map.of(
            "message", "Order accepted",
            "orderId", orderId
        ));
    }

    @PostMapping("/{orderId}/reject")
    public ResponseEntity<?> rejectOrder(
            @PathVariable UUID orderId,
            @RequestBody Map<String, String> body) {
        String reason = body.get("reason");
        if (reason == null || reason.isBlank()) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Rejection reason is required"));
        }
        
        manageOrderUseCase.rejectOrder(orderId, reason);
        return ResponseEntity.ok(Map.of(
            "message", "Order rejected",
            "orderId", orderId,
            "reason", reason
        ));
    }

    @PostMapping("/{orderId}/start-preparation")
    public ResponseEntity<?> startPreparation(@PathVariable UUID orderId) {
        manageOrderUseCase.startPreparation(orderId);
        return ResponseEntity.ok(Map.of(
            "message", "Order preparation started",
            "orderId", orderId
        ));
    }

    @PostMapping("/{orderId}/mark-ready")
    public ResponseEntity<?> markReady(@PathVariable UUID orderId) {
        manageOrderUseCase.markReady(orderId);
        return ResponseEntity.ok(Map.of(
            "message", "Order marked as ready",
            "orderId", orderId
        ));
    }
}
