package kdg.programming6.keepdishesgoing.restaurant.adapter.in;


import jakarta.validation.Valid;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.order.OrderStatusUpdateDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.RejectOrderRequest;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.order.RestaurantOrderDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.order.RestaurantOrderMapperDto;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderStatus;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.LoadRestaurantOrderByStatusUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.order.ManageOrderUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.FindAllRestaurantsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant/orders")
public class RestaurantOrderController {
    private final LoadRestaurantOrderByStatusUseCase  loadRestaurantOrderByStatusUseCase;
    private final ManageOrderUseCase manageOrderUseCase;
    private final FindAllRestaurantsUseCase findAllRestaurants;
    private final Logger logger = LoggerFactory.getLogger(RestaurantOrderController.class);


    public RestaurantOrderController(LoadRestaurantOrderByStatusUseCase loadRestaurantOrderByStatusUseCase, ManageOrderUseCase manageOrderUseCase, FindAllRestaurantsUseCase findAllRestaurants) {
        this.loadRestaurantOrderByStatusUseCase = loadRestaurantOrderByStatusUseCase;
        this.manageOrderUseCase = manageOrderUseCase;
        this.findAllRestaurants = findAllRestaurants;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<List<RestaurantOrderDto>> getOrders(@RequestParam(name = "status") String statusCsv, @AuthenticationPrincipal Jwt token) {
        String subject = token.getClaimAsString("sub");
        UUID ownerId = UUID.fromString(subject);
        var restaurants = findAllRestaurants.findAllByOwner(ownerId).stream().findFirst();
        logger.info("Check get orders with restaurant" + restaurants.toString());
        List<RestaurantOrderStatus> statuses = Arrays.stream(statusCsv.split(","))
                .map(String::trim)
                .map(RestaurantOrderStatus::valueOf)
                .toList();

        List<RestaurantOrderDto> dtos = loadRestaurantOrderByStatusUseCase
                .findAllRestaurantOrderByStatusAndRestaurantId(statuses, restaurants.get().getRestaurantId())
                .stream()
                .map(RestaurantOrderMapperDto::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
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
