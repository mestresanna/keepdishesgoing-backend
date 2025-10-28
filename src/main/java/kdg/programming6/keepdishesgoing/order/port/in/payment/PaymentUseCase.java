package kdg.programming6.keepdishesgoing.order.port.in.payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentUseCase {
    String createPayment(BigDecimal amount, UUID orderId);
    boolean verifyPayment(String sessionId, UUID orderId);
}
