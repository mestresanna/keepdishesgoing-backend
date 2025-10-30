package kdg.programming6.keepdishesgoing.order.port.in.payment;

import kdg.programming6.keepdishesgoing.order.domain.vo.PaymentSession;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentUseCase {
    PaymentSession createPayment(BigDecimal amount, UUID orderId);
    boolean verifyPayment(String sessionId, UUID orderId);
}
