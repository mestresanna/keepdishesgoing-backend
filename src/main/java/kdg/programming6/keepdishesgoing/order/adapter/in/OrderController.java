package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.dto.OrderMapperDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.dto.OrdersDto;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.port.in.order.FindOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final FindOrderUseCase  findOrderUseCase;
    private final OrderMapperDto orderMapperDto;

    public OrderController(FindOrderUseCase findOrderUseCase, OrderMapperDto orderMapperDto) {
        this.findOrderUseCase = findOrderUseCase;
        this.orderMapperDto = orderMapperDto;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersDto> getBasket(@PathVariable UUID orderId) {
        Order order = findOrderUseCase.findById(orderId);
        OrdersDto orderDto = orderMapperDto.toDto(order);
        return ResponseEntity.ok(orderDto);
    }
}
