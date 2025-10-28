package kdg.programming6.keepdishesgoing.order.port.out.payment;

import kdg.programming6.keepdishesgoing.order.core.payment.PaymentState;
import java.util.Optional;

public interface SavePaymentStatePort {
    void savePaymentState(PaymentState paymentState);
    void updatePaymentState(String stripeSessionId, String status);
    Optional<PaymentState> findByStripeSessionId(String sessionId);
}
