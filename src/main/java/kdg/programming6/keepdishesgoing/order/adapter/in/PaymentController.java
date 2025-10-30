package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.dto.PaymentSessionDto;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.vo.PaymentSession;
import kdg.programming6.keepdishesgoing.order.port.in.order.FindOrderUseCase;
import kdg.programming6.keepdishesgoing.order.port.in.payment.PaymentUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentUseCase paymentUseCase;
    private final FindOrderUseCase findOrderUseCase;

    public PaymentController(PaymentUseCase paymentUseCase, FindOrderUseCase findOrderUseCase) {
        this.paymentUseCase = paymentUseCase;
        this.findOrderUseCase = findOrderUseCase;
    }

    @PostMapping
    public PaymentSessionDto createPayment(@RequestParam UUID orderId) {
        Order order = findOrderUseCase.findById(orderId);

        if (order.getTotalPrice() == null || order.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Order is empty, cannot pay.");
        }
        var payment = paymentUseCase.createPayment(order.getTotalPrice(), orderId);
        return new PaymentSessionDto(payment.id(), payment.url()) ;
    }

    @GetMapping("/verify")
    public boolean verifyPayment(@RequestParam String sessionId, @RequestParam UUID orderId) {
        return paymentUseCase.verifyPayment(sessionId, orderId);
    }
}
