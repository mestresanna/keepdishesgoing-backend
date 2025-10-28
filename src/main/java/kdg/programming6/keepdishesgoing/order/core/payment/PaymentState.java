package kdg.programming6.keepdishesgoing.order.core.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentState(
        UUID paymentId,
        UUID orderId,
        String stripeSessionId,
        BigDecimal amount,
        String status
) {}